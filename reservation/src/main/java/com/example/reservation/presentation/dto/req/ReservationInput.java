package com.example.reservation.presentation.dto.req;

import com.example.reservation.domain.value.PaymentStatus;

import java.util.Date;

public record ReservationInput(
        Long productId,
        PaymentStatus paymentStatus,
        Date date
) {
}
