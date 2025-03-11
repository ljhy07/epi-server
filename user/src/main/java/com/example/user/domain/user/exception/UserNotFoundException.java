package com.example.user.domain.user.exception;

import com.example.user.global.exception.security.UserSecurityException;
import org.springframework.http.HttpStatus;

public class UserNotFoundException extends UserSecurityException {
    public UserNotFoundException() { super(HttpStatus.FORBIDDEN, "USER_NOT_FOUND", "유저를 찾을 수 없습니다."); }
}
