package com.example.user.domain.address.presentation.dto.res;

public record AddressResponse(
        Long addressId,
        String address,
        String receiver,
        String phone,
        String etc
) {
}
