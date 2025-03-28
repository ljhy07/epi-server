package com.example.product.product.service;

import com.example.product.product.domain.Product;
import com.example.product.product.presentation.dto.req.CreateProductInput;
import com.example.product.product.presentation.dto.req.DeleteProductInput;
import com.example.product.product.presentation.dto.req.UpdateProductInput;
import com.example.product.product.service.implementation.ProductCreator;
import com.example.product.product.service.implementation.ProductDeleter;
import com.example.product.product.service.implementation.ProductReader;
import com.example.product.product.service.implementation.ProductUpdater;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class MutationProductService {

    private final ProductCreator productCreator;
    private final ProductUpdater productUpdater;
    private final ProductDeleter productDeleter;
    private final ProductReader productReader;

    public Product create(CreateProductInput product) {
        return productCreator.save(
                Product.createBuilder()
                        .storeId(product.storeId())
                        .productName(product.productName())
                        .productPrice(product.productPrice())
                        .productDescription(product.productDescription())
                        .build()
        );
    }

    public Product update(UpdateProductInput product) {
        return productUpdater.update(
                productReader.findByProductId(product.productId()),
                Product.createBuilder()
                        .productName(product.productName())
                        .productPrice(product.productPrice())
                        .productDescription(product.productDescription())
                        .build()
        );
    }

    public Product delete(DeleteProductInput product) {
        return productDeleter.delete(
                productReader.findByProductId(product.productId())
        );
    }

}
