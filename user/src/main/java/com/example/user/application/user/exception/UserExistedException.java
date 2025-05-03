package com.example.user.application.user.exception;

import com.example.user.global.exception.UserException;
import org.springframework.http.HttpStatus;

public class UserExistedException extends UserException {
    public UserExistedException() {
        super(HttpStatus.CONFLICT, "USER_EXISTED", "유저가 이미 존재합니다.");
    }
}
