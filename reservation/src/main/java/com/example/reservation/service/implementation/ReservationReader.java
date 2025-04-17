package com.example.reservation.service.implementation;

import com.example.reservation.domain.Reservation;
import com.example.reservation.domain.repository.ReservationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReservationReader {

    private final ReservationRepository reservationRepository;

    public Reservation findById(Long reservationId) {
        return reservationRepository.findById(reservationId)
                .orElse(null);
    }

    public List<Reservation> findAllByUser(Long userId) {
        return reservationRepository.findAllByUserId(userId);
    }

    public List<Reservation> findAllByProductId(Long productId) {
        return reservationRepository.findAllByProductId(productId);
    }

}
