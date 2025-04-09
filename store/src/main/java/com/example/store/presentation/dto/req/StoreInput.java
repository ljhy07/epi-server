package com.example.store.presentation.dto.req;

public record StoreInput (
        Long storeId,
        String storeName,
        String storeAddress,
        String storeDescription
){
}
