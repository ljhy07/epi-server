package com.example.store.application.presentation.dto.req;

public record StoreInput (
        Long storeId,
        String storeName,
        String storeAddress,
        String storeDescription
){
}
