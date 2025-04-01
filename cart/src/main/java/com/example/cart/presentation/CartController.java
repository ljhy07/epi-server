package com.example.cart.presentation;

import com.example.cart.domain.Cart;
import com.example.cart.presentation.dto.req.CartMutationInput;
import com.example.cart.service.MutationCartService;
import com.example.cart.service.QueryCartService;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class CartController {

    private final MutationCartService mutationCartService;
    private final QueryCartService queryCartService;

    @QueryMapping
    public List<Cart> getCartsByUser() {
        return queryCartService.getCarts();
    }

    @MutationMapping
    public Cart createCart(
            @Argument(name = "cartMutationInput")
            CartMutationInput cartMutationInput
    ) {
        return mutationCartService.create(cartMutationInput);
    }

    @MutationMapping
    public Cart deleteCart(
            @Argument(name = "cartMutationInput")
            CartMutationInput cartMutationInput
    ) {
        return mutationCartService.delete(cartMutationInput);
    }

}
