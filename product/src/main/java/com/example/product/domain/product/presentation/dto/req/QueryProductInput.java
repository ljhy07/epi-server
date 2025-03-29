package com.example.product.domain.product.presentation.dto.req;

public record QueryProductInput(
        Long productId,
        Long storeId,
        String productName
){
}
