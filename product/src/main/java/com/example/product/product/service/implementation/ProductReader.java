package com.example.product.product.service.implementation;

import com.example.product.product.domain.Product;
import com.example.product.product.domain.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductReader {

    private final ProductRepository productRepository;

    public Product findByProductId(Long productId) {
        return productRepository.findByProductId(productId);
    }

    public List<Product> findAllByStoreId(Long storeId) {
        return productRepository.findAllByStoreId(storeId);
    }

    public List<Product> findAllByProductName(String productName) {
        return productRepository.findAllByProductName(productName);
    }

}
