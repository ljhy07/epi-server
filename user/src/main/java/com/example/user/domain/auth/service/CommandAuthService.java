package com.example.user.domain.auth.service;

import com.example.user.domain.auth.domain.Refresh;
import com.example.user.domain.auth.exception.LimitedReissueOpportunitiesException;
import com.example.user.domain.auth.presentation.dto.req.LoginInput;
import com.example.user.domain.auth.presentation.dto.req.ReissueTokenInput;
import com.example.user.domain.auth.presentation.dto.res.Token;
import com.example.user.domain.auth.service.implementation.AuthCreator;
import com.example.user.domain.auth.service.implementation.AuthDeleter;
import com.example.user.domain.auth.service.implementation.AuthReader;
import com.example.user.domain.auth.service.implementation.AuthValidator;
import com.example.user.domain.user.domain.User;
import com.example.user.domain.user.service.implementation.UserReader;
import com.example.user.global.jwt.util.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class CommandAuthService {

    private final JwtUtils jwtUtils;
    private final UserReader userReader;
    private final AuthReader authReader;
    private final AuthCreator authCreator;
    private final AuthDeleter authDeleter;
    private final AuthValidator authValidator;

    public Token login(LoginInput loginInput) {
        User user = userReader.findByEmail(loginInput.email());

        authValidator.validatePassword(loginInput.password(), user);

        return new Token(
                jwtUtils.getRefreshToken(loginInput.email()),
                jwtUtils.getAccessToken(loginInput.email())
        );
    }

    public Token reissue(ReissueTokenInput input) {
        String refreshToken = input.RefreshToken();
        Refresh refresh = authReader.findByRefreshToken(refreshToken);

        if (refresh.getExpiration() == 0){
            throw new LimitedReissueOpportunitiesException();
        }

        User user = userReader.findById(refresh.getId());
        String email = user.getEmail();

        String newAccess = jwtUtils.getAccessToken(email);
        String newRefresh = jwtUtils.getRefreshToken(email);

        authDeleter.deleteByRefresh(refreshToken);
        authCreator.createRefreshToken(
                Refresh.builder()
                        .userId(user.getId())
                        .refreshToken(newRefresh)
                        .expiration(0)
                        .build()
        );

        return new Token(
                newRefresh,
                newAccess
        );
    }

}
