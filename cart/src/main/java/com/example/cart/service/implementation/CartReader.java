package com.example.cart.service.implementation;

import com.example.cart.domain.Cart;
import com.example.cart.domain.repository.CartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CartReader {

    private final CartRepository cartRepository;

    public List<Cart> finaAllByUserId(Long userId) {
        return cartRepository.findAllByUserId(userId);
    }

    public Cart findById(Long id) {
        return cartRepository.findById(id)
                .orElse(null);
    }

}
