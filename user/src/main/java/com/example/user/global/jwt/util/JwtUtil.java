package com.example.user.global.jwt.util;

import com.example.user.domain.auth.domain.Refresh;
import com.example.user.domain.auth.domain.repository.RefreshRepository;
import com.example.user.domain.user.domain.value.Role;
import com.example.user.global.jwt.exception.ExpiredTokenException;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Component
public class JwtUtil {

    private final SecretKey secretKey;
    private final RefreshRepository refreshRepository;

    @Value("${spring.jwt.access.expiration}")
    private long accessTokenExpiration;
    @Value("${spring.jwt.refresh.expiration}")
    private long refreshTokenExpiration;

    public JwtUtil(@Value("${spring.jwt.secret}") String secret, RefreshRepository refreshRepository) {
        this.secretKey = new SecretKeySpec(secret.getBytes(StandardCharsets.UTF_8), Jwts.SIG.HS256.key().build().getAlgorithm());
        this.refreshRepository = refreshRepository;
    }

    public Long getId(String token) {
        return Long.valueOf(Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token).getPayload().get("sub", String.class));
    }

    public String getCategory(String token) {
        try {
            return Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token).getPayload().get("category", String.class);
        } catch (ExpiredJwtException e) {
            return e.getClaims().get("category", String.class);
        }
    }

    public Role getRole(String token) {
        String roleValue = Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token).getPayload().get("role", String.class);
        return Role.fromValue(roleValue);
    }

    public String getLoginType(String token) {
        try {
            return Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token).getPayload().get("loginType", String.class);
        } catch (ExpiredJwtException e) {
            return e.getClaims().get("loginType", String.class);
        }
    }

    public void isExpired(String token) {
        try {
            Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token).getPayload().getExpiration().before(new Date());
        } catch (ExpiredJwtException e) {
            throw new ExpiredTokenException();
        }
    }

    public String createAccessToken(Long id, Role role, String loginType) {
        return createJwt("access", id, role, loginType, accessTokenExpiration);
    }

    public String createRefreshToken(Long id, Role role, String loginType) {
        return createJwt("refresh", id, role, loginType, refreshTokenExpiration);
    }

    public void addRefreshToken(Long userId, String refreshToken) {

        Date date = new Date(System.currentTimeMillis() + refreshTokenExpiration);

        Refresh refresh = Refresh.builder()
                .userId(userId)
                .refreshToken(refreshToken)
                .expiration(date.toString())
                .build();

        refreshRepository.save(refresh);
    }

    private String createJwt(String category, Long id, Role role, String loginType, long expiredMs) {
        return Jwts.builder()
                .claim("category", category)
                .claim("sub", String.valueOf(id))
                .claim("role", role.getRole())
                .claim("loginType", loginType)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + expiredMs))
                .signWith(secretKey)
                .compact();
    }
}
