package com.example.user.domain.address.service.implementation;

import com.example.user.domain.address.domain.Address;
import com.example.user.domain.address.domain.repository.AddressRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AddressDeleter {

    private final AddressRepository addressRepository;

    public void delete(Address address) {
        addressRepository.delete(address);
    }
}
