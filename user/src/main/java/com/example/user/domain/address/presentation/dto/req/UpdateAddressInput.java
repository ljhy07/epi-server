package com.example.user.domain.address.presentation.dto.req;

public record UpdateAddressInput (
        Long addressId,
        String address,
        String receiver,
        String phone,
        String etc
){
}
