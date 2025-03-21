package com.example.user.domain.email.exception;

import com.example.user.global.exception.mail.UserMailException;
import org.springframework.http.HttpStatus;

public class ExpiredEmailTokenException extends UserMailException {
    public ExpiredEmailTokenException() {
        super(HttpStatus.BAD_REQUEST, "EMAIL_TOKEN_EXPIRED", "만료된 이메일 토큰입니다.");
    }
}
