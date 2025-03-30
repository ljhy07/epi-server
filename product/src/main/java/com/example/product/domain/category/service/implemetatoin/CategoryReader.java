package com.example.product.domain.category.service.implemetatoin;

import com.example.product.domain.category.domain.Category;
import com.example.product.domain.category.domain.repository.CategoryRepository;
import com.example.product.domain.category.domain.value.TopCategory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryReader {

    private final CategoryRepository categoryRepository;

    public Category findByCategoryId(Long categoryId) {
        return categoryRepository.findByCategoryId(categoryId);
    }

    public Category findByProductId(Long productId) {
        return categoryRepository.findByProductId(productId);
    }

    public List<Category> findAllByTopCategory(TopCategory topCategory) {
        return categoryRepository.findAllByTopCategory(topCategory);
    }

}
