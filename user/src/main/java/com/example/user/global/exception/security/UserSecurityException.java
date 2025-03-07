package com.example.user.global.exception.security;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;

@Getter
public class UserSecurityException extends AuthenticationException {
    private final HttpStatus status;
    private final String errorCode;

    public UserSecurityException(HttpStatus status, String errorCode, String message) {
        super(message);
        this.status = status;
        this.errorCode = errorCode;
    }
}
