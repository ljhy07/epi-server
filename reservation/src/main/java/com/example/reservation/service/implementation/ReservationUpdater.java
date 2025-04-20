package com.example.reservation.service.implementation;

import com.example.reservation.domain.Reservation;
import com.example.reservation.domain.repository.ReservationRepository;
import com.example.reservation.presentation.dto.req.ReservationInput;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class ReservationUpdater {

    private final ReservationRepository reservationRepository;

    public Reservation update(Reservation updatableReservation, ReservationInput reservationInput) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        updatableReservation.updateBuilder()
                .paymentStatus(reservationInput.paymentStatus())
                .date(dateFormat.format(new Date()))
                .build();

        return reservationRepository.save(updatableReservation);
    }
}
