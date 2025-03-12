package com.example.user.domain.mail.exception;

import com.example.user.global.exception.mail.UserMailException;
import org.springframework.http.HttpStatus;

public class EmailTokenNotFoundException extends UserMailException {
    public EmailTokenNotFoundException() {
        super(HttpStatus.NOT_FOUND, "EMAIL_TOKEN_NOT_FOUND", "유효하지 않은 이메일 토큰입니다.");
    }
}
