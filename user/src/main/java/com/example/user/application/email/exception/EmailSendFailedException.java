package com.example.user.application.email.exception;

import com.example.user.global.exception.mail.UserMailException;
import org.springframework.http.HttpStatus;

public class EmailSendFailedException extends UserMailException {
    public EmailSendFailedException() {
        super(HttpStatus.INTERNAL_SERVER_ERROR, "EMAIL_SEND_FAILED", "이메일 전송에 실패했습니다.");
    }
}
