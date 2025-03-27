package com.example.product.product.service.implementation;

import com.example.product.product.domain.Product;
import com.example.product.product.domain.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductDeleter {

    private final ProductRepository productRepository;

    public Product delete(Product product) {
        productRepository.delete(product);
        return product;
    }

}
