package com.example.review.exception;

import com.example.review.global.exception.ReviewException;
import org.springframework.http.HttpStatus;

public class UserNotCorrectException extends ReviewException {
    public UserNotCorrectException() {
        super(HttpStatus.NOT_ACCEPTABLE, "USER_IS_NOT_CORRECT", "유저가 맞지 않습니다.");
    }
}
