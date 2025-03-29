package com.example.product.domain.product.presentation.dto.req;

public record UpdateProductInput(
        Long productId,
        String productName,
        Long productPrice,
        String productDescription
) {
}
