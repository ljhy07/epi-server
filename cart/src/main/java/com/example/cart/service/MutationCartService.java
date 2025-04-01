package com.example.cart.service;

import com.example.cart.domain.Cart;
import com.example.cart.presentation.dto.req.CartMutationInput;
import com.example.cart.service.implementation.CartCreator;
import com.example.cart.service.implementation.CartDeleter;
import com.example.cart.service.implementation.CartReader;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MutationCartService {

    private final CartCreator cartCreator;
    private final CartDeleter cartDeleter;
    private final CartReader cartReader;

    public Cart create(CartMutationInput cartMutationInput) {
        Long userId = null;

        return cartCreator.createCart(
                Cart.builder()
                        .productId(cartMutationInput.productId())
                        .userId(userId)
                        .build()
        );
    }

    public Cart delete(CartMutationInput cartMutationInput) {
        return cartDeleter.delete(
                cartReader.findById(cartMutationInput.cartId())
        );
    }

}
