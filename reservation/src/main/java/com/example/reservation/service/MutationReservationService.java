package com.example.reservation.service;

import com.example.reservation.domain.Reservation;
import com.example.reservation.presentation.dto.req.ReservationInput;
import com.example.reservation.service.implementation.ReservationCreator;
import com.example.reservation.service.implementation.ReservationDeleter;
import com.example.reservation.service.implementation.ReservationReader;
import com.example.reservation.service.implementation.ReservationUpdater;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class MutationReservationService {

    private final ReservationCreator reservationCreator;
    private final ReservationUpdater reservationUpdater;
    private final ReservationDeleter reservationDeleter;
    private final ReservationReader reservationReader;

    public Reservation create(ReservationInput reservationInput) {
        Long userId = null;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        return reservationCreator.save(
                Reservation.createBuilder()
                        .userId(userId)
                        .productId(reservationInput.productId())
                        .paymentStatus(reservationInput.paymentStatus())
                        .date(dateFormat.format(reservationInput.date()))
                        .build()
        );
    }

    public Reservation update(ReservationInput reservationInput) {
        Long userId = null;

        return reservationUpdater.update(
                reservationReader.findById(reservationInput.productId()),
                reservationInput
        );
    }

    public Reservation delete(ReservationInput reservationInput) {
        return reservationDeleter.delete(
                reservationReader.findById(reservationInput.productId())
        );
    }

}
