package com.example.product.domain.category.service.implemetatoin;

import com.example.product.domain.category.domain.Category;
import com.example.product.domain.category.domain.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryUpdater {

    private final CategoryRepository categoryRepository;

    public Category update(Category updatableCategory, Category category) {
        updatableCategory.updateBuilder()
                .topCategory(category.getTopCategory())
                .normalCategory(category.getNormalCategory())
                .build();

        return categoryRepository.save(updatableCategory);
    }
}
