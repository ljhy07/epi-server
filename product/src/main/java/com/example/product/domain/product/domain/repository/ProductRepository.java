package com.example.product.domain.product.domain.repository;

import com.example.product.domain.product.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Product findByProductId(Long productId);

    List<Product> findAllByProductName(String productName);

    List<Product> findAllByStoreId(Long storeId);
}
