package com.example.cart.service;

import com.example.cart.domain.Cart;
import com.example.cart.service.implementation.CartReader;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class QueryCartService {

    private final CartReader cartReader;

    public List<Cart> getCarts() {
        Long userId = null;

        return cartReader.finaAllByUserId(userId);
    }

}
