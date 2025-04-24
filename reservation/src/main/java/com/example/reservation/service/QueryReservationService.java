package com.example.reservation.service;

import com.example.reservation.domain.Reservation;
import com.example.reservation.global.jwt.util.JwtUtils;
import com.example.reservation.presentation.dto.req.ReservationInput;
import com.example.reservation.service.implementation.ReservationReader;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class QueryReservationService {

    private final ReservationReader reservationReader;
    private final JwtUtils jwtUtils;

    public Reservation getReservation(String accessToken, ReservationInput reservationInput) {
        return reservationReader.findById(reservationInput.productId());
    }

    public List<Reservation> getReservationsByUser(String accessToken) {
        Long userId = jwtUtils.getTokenSub();
        return reservationReader.findAllByUser(userId);
    }

    public List<Reservation> getReservationsByProductId(String accessToken, ReservationInput reservationInput) {
        return reservationReader.findAllByProductId(reservationInput.productId());
    }

}
