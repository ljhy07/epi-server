package com.example.user.domain.address.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Address {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long addressId;

    private Long userId;

    private String address;

    private String receiver;

    private String phone;

    private String etc;

    @Builder(builderMethodName = "createBuilder")
    public Address(Long userId, String address, String receiver, String phone, String etc) {
        this.userId = userId;
        this.address = address;
        this.receiver = receiver;
        this.phone = phone;
        this.etc = etc;
    }

    @Builder(builderMethodName = "updateBuilder")
    public Address(String address, String receiver, String phone, String etc) {
        this.address = address;
        this.receiver = receiver;
        this.phone = phone;
        this.etc = etc;
    }

}
