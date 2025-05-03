package com.example.user.application.address.service.implementation;

import com.example.user.application.address.domain.Address;
import com.example.user.application.address.domain.repository.AddressRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AddressReader {

    private final AddressRepository addressRepository;

    public Address findByAddressId(Long addressId) {
        return addressRepository.findByAddressId(addressId);
    }

    public List<Address> findAllByUserId(Long userId) {
        return addressRepository.findAllByUserId(userId);
    }

}
