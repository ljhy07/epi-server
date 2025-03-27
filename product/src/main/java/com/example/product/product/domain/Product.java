package com.example.product.product.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity(name = "product")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Product {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;

    private Long storeId;

    private String productName;

    private Long productPrice;

    @Lob
    private String productDescription;

    @Builder(builderMethodName = "createBuilder")
    public Product(Long storeId, String productName, Long productPrice, String productDescription) {
        this.storeId = storeId;
        this.productName = productName;
        this.productPrice = productPrice;
        this.productDescription = productDescription;
    }

    @Builder(builderMethodName = "updateBuilder")
    public Product(String productName, Long productPrice, String productDescription) {
        this.productName = productName;
        this.productPrice = productPrice;
        this.productDescription = productDescription;
    }

}
