package com.example.reservation.service;

import com.example.reservation.domain.Reservation;
import com.example.reservation.global.jwt.util.JwtUtils;
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
    private final JwtUtils jwtUtils;

    public Reservation create(String accessToken, ReservationInput reservationInput) {
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

    public Reservation update(String accessToken, ReservationInput reservationInput) {
        Long userId = jwtUtils.getTokenSub(accessToken);

        return reservationUpdater.update(
                reservationReader.findById(reservationInput.productId()),
                reservationInput
        );
    }

    public Reservation delete(String accessToken, ReservationInput reservationInput) {
        Long userId = jwtUtils.getTokenSub(accessToken);
        Reservation reservation = reservationReader.findById(reservationInput.productId());

        if (userId.equals(reservation.getUserId())) {
            return reservationDeleter.delete(
                    reservation
            );
        } else {
            return null;
        }
    }

}
