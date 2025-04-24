package com.example.reservation.global.jwt.exception;

import com.example.reservation.global.exception.security.ReservationSecurityException;
import org.springframework.http.HttpStatus;

public class InvalidTokenException extends ReservationSecurityException {
    public InvalidTokenException() {
      super(HttpStatus.UNAUTHORIZED, "INVALID_TOKEN", "잘못된 토큰입니다.");
    }
}
