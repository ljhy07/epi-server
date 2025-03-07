package com.example.user.domain.auth.service.implementation;

import com.example.user.domain.auth.domain.repository.RefreshRepository;
import com.example.user.domain.auth.presentation.dto.req.ReissueTokenInput;
import com.example.user.domain.auth.presentation.dto.res.Token;
import com.example.user.domain.user.domain.value.Role;
import com.example.user.global.jwt.exception.InvalidTokenException;
import com.example.user.global.jwt.exception.RefreshTokenNotFoundException;
import com.example.user.global.jwt.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ReIssuer {

    private final JwtUtil jwtUtil;
    private final RefreshRepository refreshRepository;

    public Token reissue(ReissueTokenInput input) {

        String refresh = input.RefreshToken();

        if (refresh == null) {
            log.warn("Refresh token not found in cookies");
            throw new RefreshTokenNotFoundException();
        }

        jwtUtil.isExpired(refresh);

        String category = jwtUtil.getCategory(refresh);

        if (!category.equals("refresh")) {
            log.warn("Invalid token category: {}", category);
            throw new InvalidTokenException();
        }

        Boolean isExist = refreshRepository.existsByRefreshToken(refresh);

        if (!isExist) {
            log.warn("Refresh token not found in database: {}", refresh);
            throw new RefreshTokenNotFoundException();
        }

        String loginType = jwtUtil.getLoginType(refresh);

        Long id = jwtUtil.getId(refresh);
        Role role = jwtUtil.getRole(refresh);

        String newAccess = jwtUtil.createAccessToken(id, role, loginType);
        String newRefresh = jwtUtil.createRefreshToken(id, role, loginType);

        refreshRepository.deleteByRefreshToken(refresh);
        jwtUtil.addRefreshToken(id, newRefresh);

        return new Token(
                newRefresh,
                newAccess
        );
    }

}
