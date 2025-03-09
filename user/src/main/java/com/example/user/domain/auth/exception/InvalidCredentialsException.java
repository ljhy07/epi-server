package com.example.user.domain.auth.exception;

import com.example.user.global.exception.security.UserSecurityException;
import org.springframework.http.HttpStatus;

public class InvalidCredentialsException extends UserSecurityException {
    public InvalidCredentialsException() {
        super(HttpStatus.UNAUTHORIZED, "INVALID_CREDENTIALS", "잘못된 이메일 또는 비밀번호입니다.");
    }
}
