package com.example.reservation.service;

import com.example.reservation.domain.Reservation;
import com.example.reservation.presentation.dto.req.ReservationInput;
import com.example.reservation.service.implementation.ReservationReader;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class QueryReservationService {

    private final ReservationReader reservationReader;

    public Reservation getReservation(ReservationInput reservationInput) {
        return reservationReader.findById(reservationInput.productId());
    }

    public List<Reservation> getReservationsByUser() {
        Long userId = null;
        return reservationReader.findAllByUser(userId);
    }

    public List<Reservation> getReservationsByProductId(ReservationInput reservationInput) {
        return reservationReader.findAllByProductId(reservationInput.productId());
    }

}
