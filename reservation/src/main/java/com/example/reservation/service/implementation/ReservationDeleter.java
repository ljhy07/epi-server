package com.example.reservation.service.implementation;

import com.example.reservation.domain.Reservation;
import com.example.reservation.domain.repository.ReservationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReservationDeleter {

    private final ReservationRepository reservationRepository;

    public Reservation delete(Reservation reservation) {
        reservationRepository.delete(reservation);
        return reservation;
    }

}
