package com.example.reservation.presentation;

import com.example.reservation.domain.Reservation;
import com.example.reservation.presentation.dto.req.ReservationInput;
import com.example.reservation.service.MutationReservationService;
import com.example.reservation.service.QueryReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ReservationController {

    private final MutationReservationService mutationReservationService;
    private final QueryReservationService queryReservationService;

    @MutationMapping
    public Reservation createReservation(
            @Argument(name = "reservationInput")
            ReservationInput reservationInput
    ) {
        return mutationReservationService.create(reservationInput);
    }

    @MutationMapping
    public Reservation updateReservation(
            @Argument(name = "reservationInput")
            ReservationInput reservationInput
    ) {
        return mutationReservationService.update(reservationInput);
    }

    @MutationMapping
    public Reservation deleteReservation(
            @Argument(name = "reservationInput")
            ReservationInput reservationInput
    ) {
        return mutationReservationService.delete(reservationInput);
    }

    @QueryMapping
    public Reservation getReservation(
            @Argument(name = "reservationInput")
            ReservationInput reservationInput
    ) {
        return queryReservationService.getReservation(reservationInput);
    }

    @QueryMapping
    public List<Reservation> getReservationsByUser(){
        return queryReservationService.getReservationsByUser();
    }

    @QueryMapping
    public List<Reservation> getReservationsByProduct(
            @Argument(name = "reservationInput")
            ReservationInput reservationInput
    ){
        return queryReservationService.getReservationsByProductId(reservationInput);
    }

}
