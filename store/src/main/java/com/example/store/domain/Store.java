package com.example.store.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity(name = "store")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Store {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long storeId;

    private Long userId;

    private String storeName;

    private String storeAddress;

    private String storeDescription;

    @Builder(builderMethodName = "createStoreBuilder")
    public Store(Long userId, String storeName, String storeAddress, String storeDescription) {
        this.userId = userId;
        this.storeName = storeName;
        this.storeAddress = storeAddress;
        this.storeDescription = storeDescription;
    }

    @Builder(builderMethodName = "updateStoreBuilder")
    public Store(String storeName, String storeAddress, String storeDescription, Long storeId) {
        this.storeName = storeName;
        this.storeAddress = storeAddress;
        this.storeDescription = storeDescription;
    }

}
