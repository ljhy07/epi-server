package com.example.reservation.service.implementation;

import com.example.reservation.domain.Reservation;
import com.example.reservation.domain.repository.ReservationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class ReservationUpdater {

    private final ReservationRepository reservationRepository;

    public Reservation update(Reservation updatableReservation, Date date) {
        updatableReservation.updateBuilder()
                .date(date)
                .build();

        return reservationRepository.save(updatableReservation);
    }
}
