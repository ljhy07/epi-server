package com.example.reservation.global.jwt.exception;

import com.example.reservation.global.exception.security.ReservationSecurityException;
import org.springframework.http.HttpStatus;

public class ExpiredTokenException extends ReservationSecurityException {
    public ExpiredTokenException() {
        super(HttpStatus.UNAUTHORIZED, "EXPIRED_TOKEN", "만료된 토큰입니다.");
    }
}
