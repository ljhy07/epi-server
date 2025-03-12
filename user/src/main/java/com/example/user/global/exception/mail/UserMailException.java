package com.example.user.global.exception.mail;

import com.example.user.global.exception.UserException;
import org.springframework.http.HttpStatus;

public class UserMailException extends UserException {
    public UserMailException(HttpStatus status, String errorCode, String message) {
        super(status, errorCode, message);
    }
}
