package com.example.user.domain.address.domain.repository;

import com.example.user.domain.address.domain.Address;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {
    Address findByAddressId(Long addressId);

    List<Address> findAllByUserId(@NotNull Long userId);
}
