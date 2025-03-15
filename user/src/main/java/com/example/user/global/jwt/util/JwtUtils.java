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
import org.springframework.core.io.FileSystemResource;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Component;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.KeyFactory;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.Date;

@Slf4j
@Component
public class JwtUtils {

    private final CustomUserDetailsService customUserDetailsService;
    private final AuthCreator authCreator;
    private final UserReader userReader;

    private final RSAPrivateKey privateKey;
    private final RSAPublicKey publicKey;

    public JwtUtils(
            UserReader userReader,
            CustomUserDetailsService customUserDetailsService,
            AuthCreator authCreator
    ) throws Exception {
        this.customUserDetailsService = customUserDetailsService;
        this.userReader = userReader;
        this.authCreator = authCreator;

        String privateKeyContent = new String(Files.readAllBytes(Paths.get(new FileSystemResource("env/private_key_pkcs8.pem").getURI())));
        String publicKeyContent = new String(Files.readAllBytes(Paths.get(new FileSystemResource("env/public_key.pem").getURI())));

        privateKeyContent = privateKeyContent
                .replaceAll("\\s", "");
        publicKeyContent = publicKeyContent
                .replaceAll("\\s", "");

        KeyFactory keyFactory = KeyFactory.getInstance("RSA");

        PKCS8EncodedKeySpec keySpecPKCS8 = new PKCS8EncodedKeySpec(Base64.getDecoder().decode(privateKeyContent));
        this.privateKey = (RSAPrivateKey) keyFactory.generatePrivate(keySpecPKCS8);

        X509EncodedKeySpec keySpecX509 = new X509EncodedKeySpec(Base64.getDecoder().decode(publicKeyContent));
        this.publicKey = (RSAPublicKey) keyFactory.generatePublic(keySpecX509);
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
