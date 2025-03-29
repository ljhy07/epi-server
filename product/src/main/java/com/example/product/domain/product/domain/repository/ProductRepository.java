package com.example.product.domain.product.domain.repository;

import com.example.product.domain.product.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Product findByProductId(Long productId);

    List<Product> findAllByProductName(String productName);

    List<Product> findAllByStoreId(Long storeId);
}
