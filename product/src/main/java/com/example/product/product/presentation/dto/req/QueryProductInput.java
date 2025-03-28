package com.example.product.product.presentation.dto.req;

public record QueryProductInput(
        Long productId,
        Long storeId,
        String productName
){
}
