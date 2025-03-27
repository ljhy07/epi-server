package com.example.product.product.service.implementation;

import com.example.product.product.domain.Product;
import com.example.product.product.domain.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductUpdater {

    private final ProductRepository productRepository;

    public Product update(Product updatableProduct, Product product) {
        updatableProduct.updateBuilder()
                .productName(product.getProductName())
                .productPrice(product.getProductPrice())
                .productDescription(product.getProductDescription())
                .build();

        return productRepository.save(updatableProduct);
    }

}
