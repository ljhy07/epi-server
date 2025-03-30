package com.example.product.domain.category.service.implemetatoin;

import com.example.product.domain.category.domain.Category;
import com.example.product.domain.category.domain.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryCreator {

    private final CategoryRepository categoryRepository;

    public Category save(Category category) {
        return categoryRepository.save(category);
    }

}
