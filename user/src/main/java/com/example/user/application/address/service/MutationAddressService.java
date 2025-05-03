package com.example.user.application.address.service;

import com.example.user.application.address.domain.Address;
import com.example.user.application.address.presentation.dto.req.CreateAddressInput;
import com.example.user.application.address.presentation.dto.req.DeleteAddressInput;
import com.example.user.application.address.presentation.dto.req.UpdateAddressInput;
import com.example.user.application.address.presentation.dto.res.AddressResponse;
import com.example.user.application.address.service.implementation.AddressCreator;
import com.example.user.application.address.service.implementation.AddressDeleter;
import com.example.user.application.address.service.implementation.AddressReader;
import com.example.user.application.address.service.implementation.AddressUpdater;
import com.example.user.application.user.domain.User;
import com.example.user.global.auth.util.AuthUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class MutationAddressService {

    private final AuthUtils authUtils;
    private final AddressCreator addressCreator;
    private final AddressUpdater addressUpdater;
    private final AddressDeleter addressDeleter;
    private final AddressReader addressReader;

    public AddressResponse createAddress(CreateAddressInput createAddressInput) {
        User user = authUtils.getCurrentUser();

        Address address = addressCreator.save(
                Address.createBuilder()
                        .userId(user.getId())
                        .address(createAddressInput.address())
                        .receiver(createAddressInput.receiver())
                        .phone(createAddressInput.phone())
                        .etc(createAddressInput.etc())
                        .build()
        );

        return new AddressResponse(
                address.getAddressId(),
                address.getAddress(),
                address.getReceiver(),
                address.getPhone(),
                address.getEtc()
        );
    }

    public AddressResponse updateAddress(UpdateAddressInput updateAddressInput) {
        Address address = addressUpdater.update(
                Address.updateBuilder()
                        .address(updateAddressInput.address())
                        .receiver(updateAddressInput.receiver())
                        .phone(updateAddressInput.phone())
                        .etc(updateAddressInput.etc())
                        .build(),
                addressReader.findByAddressId(updateAddressInput.addressId())
        );

        return new AddressResponse(
                address.getAddressId(),
                address.getAddress(),
                address.getReceiver(),
                address.getPhone(),
                address.getEtc()
        );
    }

    public AddressResponse deleteAddress(DeleteAddressInput deleteAddressInput) {
        Address address = addressReader.findByAddressId(deleteAddressInput.addressId());

        addressDeleter.delete(
                address
        );

        return new AddressResponse(
                address.getAddressId(),
                address.getAddress(),
                address.getReceiver(),
                address.getPhone(),
                address.getEtc()
        );
    }

}
