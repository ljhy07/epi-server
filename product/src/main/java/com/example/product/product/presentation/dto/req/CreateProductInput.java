package com.example.product.product.presentation.dto.req;

public record CreateProductInput(
        Long storeId,
        String productName,
        Long productPrice,
        String productDescription
) {
}
