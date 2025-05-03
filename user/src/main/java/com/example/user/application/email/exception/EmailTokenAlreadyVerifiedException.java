package com.example.user.application.email.exception;

import com.example.user.global.exception.mail.UserMailException;
import org.springframework.http.HttpStatus;

public class EmailTokenAlreadyVerifiedException extends UserMailException {
    public EmailTokenAlreadyVerifiedException() {
        super(HttpStatus.BAD_REQUEST, "EMAIL_TOKEN_ALREADY_VERIFIED", "이미 인증된 토큰입니다.");
    }
}
