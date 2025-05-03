package com.example.user.application.address.service.implementation;

import com.example.user.application.address.domain.Address;
import com.example.user.application.address.domain.repository.AddressRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AddressUpdater {

    private final AddressRepository addressRepository;

    public Address update(Address updatableAddress, Address address) {
        updatableAddress.updateBuilder()
                .address(address.getAddress())
                .receiver(address.getReceiver())
                .phone(address.getPhone())
                .etc(address.getEtc())
                .build();

        return addressRepository.save(updatableAddress);
    }
}
