package com.example.user.application.address.service;

import com.example.user.application.address.domain.Address;
import com.example.user.application.address.presentation.dto.req.AddressInput;
import com.example.user.application.address.presentation.dto.res.AddressResponse;
import com.example.user.application.address.service.implementation.AddressReader;
import com.example.user.global.auth.util.AuthUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class QueryAddressService {

    private final AuthUtils authUtils;
    private final AddressReader addressReader;

    public AddressResponse getAddressByAddressId(AddressInput addressInput) {
        Address address = addressReader.findByAddressId(addressInput.addressId());

        return new AddressResponse(
                address.getAddressId(),
                address.getAddress(),
                address.getReceiver(),
                address.getPhone(),
                address.getEtc()
        );
    }

    public List<AddressResponse> getAddressesByUserId() {
        List<Address> addresses = addressReader.findAllByUserId(
                authUtils.getCurrentUser().getId()
        );
        List<AddressResponse> addressResponse = new ArrayList<>();

        for (Address address : addresses) {
            addressResponse.add(new AddressResponse(
                    address.getAddressId(),
                    address.getAddress(),
                    address.getReceiver(),
                    address.getPhone(),
                    address.getEtc()
            ));
        }

        return addressResponse;
    }

}
