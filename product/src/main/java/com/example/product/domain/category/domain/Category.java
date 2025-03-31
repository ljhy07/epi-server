package com.example.product.domain.category.domain;

import com.example.product.domain.category.domain.value.TopCategory;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity(name = "category")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Category {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long categoryId;

    private Long productId;

    private TopCategory topCategory;

    private String normalCategory;


    @Builder(builderMethodName = "createBuilder")
    public Category(Long productId, TopCategory topCategory, String normalCategory) {
        this.productId = productId;
        this.topCategory = topCategory;
        this.normalCategory = normalCategory;
    }

    @Builder(builderMethodName = "updateBuilder")
    public Category(TopCategory topCategory, String normalCategory) {
        this.topCategory = topCategory;
        this.normalCategory = normalCategory;
    }

}
