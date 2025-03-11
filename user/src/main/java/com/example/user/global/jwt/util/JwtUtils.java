package com.example.user.global.jwt.util;

import com.example.user.domain.auth.domain.Refresh;
import com.example.user.domain.auth.service.CustomUserDetailsService;
import com.example.user.domain.auth.service.dto.CustomUserDetails;
import com.example.user.domain.auth.service.implementation.AuthCreator;
import com.example.user.domain.user.domain.User;
import com.example.user.domain.user.service.implementation.UserReader;
import com.example.user.global.jwt.exception.ExpiredTokenException;
import com.example.user.global.jwt.exception.InvalidTokenException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Component;

import java.io.File;
import java.nio.file.Files;
import java.security.KeyFactory;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Date;

@Slf4j
@Component
public class JwtUtils {

    private final CustomUserDetailsService customUserDetailsService;
    private final AuthCreator authCreator;
    private final UserReader userReader;

    private final RSAPrivateKey privateKey;
    private final RSAPublicKey publicKey;

    @Value("${spring.jwt.private-key-path}")
    private String privateKeyPath;
    @Value("${spring.jwt.public-key-path}")
    private String publicKeyPath;

    public JwtUtils(
            UserReader userReader,
            CustomUserDetailsService customUserDetailsService,
            AuthCreator authCreator
    ) throws Exception {
        this.customUserDetailsService = customUserDetailsService;
        this.userReader = userReader;
        this.authCreator = authCreator;

        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        byte[] privateKeyBytes = Files.readAllBytes(new File(privateKeyPath).toPath());
        byte[] publicKeyBytes = Files.readAllBytes(new File(publicKeyPath).toPath());

        privateKey = (RSAPrivateKey) keyFactory.generatePrivate(new PKCS8EncodedKeySpec(privateKeyBytes));
        publicKey = (RSAPublicKey) keyFactory.generatePublic(new X509EncodedKeySpec(publicKeyBytes));
    }

    public Claims validateToken(String token) {
        return Jwts.parser()
                .verifyWith(publicKey) // 공개키로 검증
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    public String getAccessToken(String email){
        return createAccessToken(email);
    }

    public String getRefreshToken(String email){
        return createRefreshToken(email);
    }

    private String createAccessToken(String email) {
        User user = userReader.findByEmail(email);

        return Jwts.builder()
                .claim("sub", user.getId()) // subject
                .claim("role", user.getRole()) // role
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + 60000))
                .signWith(privateKey, SignatureAlgorithm.RS256)
                .compact();
    }

    private String createRefreshToken(String email) {
        User user = userReader.findByEmail(email);

        String refreshToken = Jwts.builder()
                .claim("sub", user.getId()) // subject
                .claim("role", user.getRole()) // role
                .claim("em", user.getEmail()) // email
                .claim("us", 1) // is_use
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + 604800000))
                .signWith(privateKey, SignatureAlgorithm.RS256)
                .compact();

        authCreator.createRefreshToken(
                Refresh.builder()
                        .userId(user.getId())
                        .refreshToken(refreshToken)
                        .expiration(1)
                        .build()
        );

        return refreshToken;
    }

    public UsernamePasswordAuthenticationToken authorization(String token) {
        CustomUserDetails userDetails = customUserDetailsService.loadUserByUsername(getTokenEm(token));
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    private String getTokenSub(String token){
        return getTokenBody(token).get("sub", String.class);
    }

    private String getRole(String token){
        return getTokenBody(token).get("role", String.class);
    }

    private String getTokenEm(String token){
        return getTokenBody(token).get("em", String.class);
    }

    private String getTokenUs(String token){
        return getTokenBody(token).get("us", String.class);
    }

    private Claims getTokenBody(String token){
        try {
            Claims tokenBody = Jwts.parser()
                    .verifyWith(publicKey) // 공개키로 검증
                    .build()
                    .parseSignedClaims(token)
                    .getPayload();

            log.info("Token Body: {}", tokenBody.toString());
            return tokenBody;
        } catch(io.jsonwebtoken.ExpiredJwtException e) {
            throw new ExpiredTokenException();
        } catch (Exception e) {
            throw new InvalidTokenException();
        }
    }

}
