package com.example.user.global.jwt.exception;

import com.example.user.global.exception.security.UserSecurityException;
import org.springframework.http.HttpStatus;

public class ExpiredTokenException extends UserSecurityException {
    public ExpiredTokenException() {
        super(HttpStatus.UNAUTHORIZED, "EXPIRED_TOKEN", "만료된 토큰입니다.");
    }
}
