package com.example.cart.service.implementation;

import com.example.cart.domain.Cart;
import com.example.cart.domain.repository.CartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CartDeleter {

    private final CartRepository cartRepository;

    public Cart delete(Cart cart) {
        cartRepository.delete(cart);
        return cart;
    }

}
