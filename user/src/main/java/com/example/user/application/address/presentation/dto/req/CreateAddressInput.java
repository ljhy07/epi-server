package com.example.user.application.address.presentation.dto.req;

public record CreateAddressInput(
        String address,
        String receiver,
        String phone,
        String etc
) {
}
