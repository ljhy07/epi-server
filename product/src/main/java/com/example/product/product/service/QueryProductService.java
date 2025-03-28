package com.example.product.product.service;

import com.example.product.product.domain.Product;
import com.example.product.product.presentation.dto.req.QueryProductInput;
import com.example.product.product.service.implementation.ProductReader;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class QueryProductService {

    private final ProductReader productReader;

    public Product getProduct(QueryProductInput queryProductInput) {
        return productReader.findByProductId(queryProductInput.productId());
    }

    public List<Product> getProducts(QueryProductInput queryProductInput) {
        return productReader.findAllByStoreId(queryProductInput.storeId());
    }

    public List<Product> getProductsByProductName(QueryProductInput queryProductInput) {
        return productReader.findAllByProductName(queryProductInput.productName());
    }

}
