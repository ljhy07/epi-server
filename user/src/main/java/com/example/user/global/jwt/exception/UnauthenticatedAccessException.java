package com.example.user.global.jwt.exception;

import com.example.user.global.exception.security.UserSecurityException;
import org.springframework.http.HttpStatus;

public class UnauthenticatedAccessException extends UserSecurityException {
    public UnauthenticatedAccessException() {
        super(HttpStatus.UNAUTHORIZED, "UNAUTHENTICATED_ACCESS", "인증이 필요합니다.");
    }
}
