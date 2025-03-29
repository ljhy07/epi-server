package com.example.product.domain.product.presentation.dto.req;

public record CreateProductInput(
        Long storeId,
        String productName,
        Long productPrice,
        String productDescription
) {
}
