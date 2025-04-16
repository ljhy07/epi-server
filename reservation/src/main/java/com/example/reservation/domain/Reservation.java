package com.example.reservation.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@Getter
@Entity(name = "reservation")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Reservation {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reservationId;

    private Long userId;

    private Long productId;

    private Date date;

    @Builder(builderMethodName = "createBuilder")
    public Reservation(Long userId, Long productId, Date date) {
        this.userId = userId;
        this.productId = productId;
        this.date = date;
    }

    @Builder(builderMethodName = "updateBuilder")
    public Reservation(Date date) {
        this.date = date;
    }

}
