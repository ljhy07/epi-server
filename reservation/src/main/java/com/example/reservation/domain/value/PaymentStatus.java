package com.example.reservation.domain.value;

import lombok.Getter;

@Getter
public enum PaymentStatus{
    Online("online"),
    Offline("offline");

    private final String paymentStatus;

    PaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public static PaymentStatus fromValue(String value) {
        for (PaymentStatus paymentStatus : PaymentStatus.values()) {
            if (paymentStatus.name().equalsIgnoreCase(value) || paymentStatus.getPaymentStatus().equalsIgnoreCase(value)) {
                return paymentStatus;
            }
        }
        throw new IllegalArgumentException("Invalid paymentStatus value: " + value);
    }
}
