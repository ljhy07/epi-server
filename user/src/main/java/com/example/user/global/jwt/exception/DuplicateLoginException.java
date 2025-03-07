package com.example.user.global.jwt.exception;

import com.example.user.global.exception.security.UserSecurityException;
import org.springframework.http.HttpStatus;

public class DuplicateLoginException extends UserSecurityException {
    public DuplicateLoginException() {
        super(HttpStatus.UNAUTHORIZED, "DUPLICATE_LOGIN", "이미 로그인한 상태입니다.");
    }
}
