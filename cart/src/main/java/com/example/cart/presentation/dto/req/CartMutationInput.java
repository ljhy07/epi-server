package com.example.cart.presentation.dto.req;

public record CartMutationInput(
        Long cartId,
        Long productId
) {
}
