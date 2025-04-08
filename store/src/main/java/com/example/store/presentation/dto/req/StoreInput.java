package com.example.store.presentation.dto.req;

public record StoreInput (
        Long storeId,
        Long userId,
        String storeName,
        String storeAddress,
        String storeDescription
){
}
