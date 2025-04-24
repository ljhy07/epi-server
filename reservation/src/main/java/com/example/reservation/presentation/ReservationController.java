package com.example.reservation.presentation;

import com.example.reservation.domain.Reservation;
import com.example.reservation.presentation.dto.req.ReservationInput;
import com.example.reservation.service.MutationReservationService;
import com.example.reservation.service.QueryReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ReservationController {

    private final MutationReservationService mutationReservationService;
    private final QueryReservationService queryReservationService;

    @MutationMapping
    public Reservation createReservation(
            @Header String accessToken,
            @Argument(name = "reservationInput")
            ReservationInput reservationInput
    ) {
        return mutationReservationService.create(accessToken, reservationInput);
    }

    @MutationMapping
    public Reservation updateReservation(
            @Header String accessToken,
            @Argument(name = "reservationInput")
            ReservationInput reservationInput
    ) {
        return mutationReservationService.update(accessToken, reservationInput);
    }

    @MutationMapping
    public Reservation deleteReservation(
            @Header String accessToken,
            @Argument(name = "reservationInput")
            ReservationInput reservationInput
    ) {
        return mutationReservationService.delete(accessToken, reservationInput);
    }

    @QueryMapping
    public Reservation getReservation(
            @Header String accessToken,
            @Argument(name = "reservationInput")
            ReservationInput reservationInput
    ) {
        return queryReservationService.getReservation(accessToken, reservationInput);
    }

    @QueryMapping
    public List<Reservation> getReservationsByUser(
            @Header String accessToken
    ){
        return queryReservationService.getReservationsByUser(accessToken);
    }

    @QueryMapping
    public List<Reservation> getReservationsByProduct(
            @Header String accessToken,
            @Argument(name = "reservationInput")
            ReservationInput reservationInput
    ){
        return queryReservationService.getReservationsByProductId(accessToken, reservationInput);
    }

}
