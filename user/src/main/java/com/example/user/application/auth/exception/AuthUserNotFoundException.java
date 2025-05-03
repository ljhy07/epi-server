package com.example.user.application.auth.exception;

import com.example.user.global.exception.security.UserSecurityException;
import org.springframework.http.HttpStatus;

public class AuthUserNotFoundException extends UserSecurityException {
    public AuthUserNotFoundException() {
        super(HttpStatus.NOT_FOUND, "USER_NOT_FOUND","유저를 찾을 수 없습니다.");
    }
}
