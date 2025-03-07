package com.example.user.global.jwt.exception;

import com.example.user.global.exception.security.UserSecurityException;
import org.springframework.http.HttpStatus;

public class CustomAccessDeniedException extends UserSecurityException {
    public CustomAccessDeniedException() {
      super(HttpStatus.FORBIDDEN, "ACCESS_DENIED", "권한이 필요합니다.");
    }
}
