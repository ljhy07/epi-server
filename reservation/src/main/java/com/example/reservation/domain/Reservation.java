package com.example.reservation.domain;

import com.example.reservation.domain.value.PaymentStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity(name = "reservation")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Reservation {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reservationId;

    private Long userId;

    private Long productId;

    private PaymentStatus paymentStatus;

    private String date;

    @Builder(builderMethodName = "createBuilder")
    public Reservation(Long userId, Long productId, PaymentStatus paymentStatus, String date) {
        this.userId = userId;
        this.productId = productId;
        this.paymentStatus = paymentStatus;
        this.date = date;
    }

    @Builder(builderMethodName = "updateBuilder")
    public Reservation(PaymentStatus paymentStatus, String date) {
        this.paymentStatus = paymentStatus;
        this.date = date;
    }

}
