package com.example.reservation.global.jwt.util;

import com.example.reservation.global.jwt.exception.ExpiredTokenException;
import com.example.reservation.global.jwt.exception.InvalidTokenException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Component;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.KeyFactory;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

@Slf4j
@Component
public class JwtUtils {

    private final RSAPublicKey publicKey;

    public JwtUtils() throws Exception {
        String publicKeyContent = new String(Files.readAllBytes(Paths.get(new FileSystemResource("env/public_key.pem").getURI())));

        publicKeyContent = publicKeyContent
                .replaceAll("\\s", "");

        KeyFactory keyFactory = KeyFactory.getInstance("RSA");

        X509EncodedKeySpec keySpecX509 = new X509EncodedKeySpec(Base64.getDecoder().decode(publicKeyContent));
        this.publicKey = (RSAPublicKey) keyFactory.generatePublic(keySpecX509);
    }

    public Long getTokenSub(String token){
        return getTokenBody(token).get("sub", Long.class);
    }

    public String getTokenEm(String token){
        return getTokenBody(token).get("em", String.class);
    }

    private Claims getTokenBody(String token){
        try {
            return Jwts.parser()
                    .verifyWith(publicKey) // 공개키로 검증
                    .build()
                    .parseSignedClaims(token)
                    .getPayload();
        } catch(io.jsonwebtoken.ExpiredJwtException e) {
            throw new ExpiredTokenException();
        } catch (Exception e) {
            throw new InvalidTokenException();
        }
    }

}

