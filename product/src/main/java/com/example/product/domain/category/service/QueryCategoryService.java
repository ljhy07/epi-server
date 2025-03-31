package com.example.product.domain.category.service;

import com.example.product.domain.category.domain.Category;
import com.example.product.domain.category.presentation.dto.req.QueryCategoryInput;
import com.example.product.domain.category.service.implemetatoin.CategoryReader;
import com.example.product.domain.product.domain.Product;
import com.example.product.domain.product.service.implementation.ProductReader;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class QueryCategoryService {

    private final CategoryReader categoryReader;
    private final ProductReader productReader;

    public Category getCategoryById(QueryCategoryInput input) {
        return categoryReader.findByCategoryId(input.categoryId());
    }

    public Category getCategoryByProduct(QueryCategoryInput input) {
        return categoryReader.findByProductId(input.productId());
    }

    public List<Category> getCategoriesByTopCategory(QueryCategoryInput input){
        return categoryReader.findAllByTopCategory(input.topCategory());
    }

    public List<Product> getProductsByCategory(QueryCategoryInput input){
        List<Category> categories = categoryReader.findAllByTopCategory(input.topCategory());
        List<Product> products = new ArrayList<>();

        for (Category category : categories) {
            products.add(
                    productReader.findByProductId(category.getProductId())
            );
        }

        return products;
    }

}
