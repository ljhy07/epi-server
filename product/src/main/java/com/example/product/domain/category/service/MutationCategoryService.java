package com.example.product.domain.category.service;

import com.example.product.domain.category.domain.Category;
import com.example.product.domain.category.presentation.dto.req.CreateCategoryInput;
import com.example.product.domain.category.presentation.dto.req.DeleteCategoryInput;
import com.example.product.domain.category.presentation.dto.req.UpdateCategoryInput;
import com.example.product.domain.category.service.implemetatoin.CategoryCreator;
import com.example.product.domain.category.service.implemetatoin.CategoryDeleter;
import com.example.product.domain.category.service.implemetatoin.CategoryReader;
import com.example.product.domain.category.service.implemetatoin.CategoryUpdater;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MutationCategoryService {

    private final CategoryCreator categoryCreator;
    private final CategoryUpdater categoryUpdater;
    private final CategoryDeleter categoryDeleter;
    private final CategoryReader categoryReader;

    public Category create(CreateCategoryInput input) {
        return categoryCreator.save(
                Category.createBuilder()
                        .productId(input.productId())
                        .topCategory(input.topCategory())
                        .normalCategory(input.normalCategory())
                        .build()
        );
    }

    public Category update(UpdateCategoryInput input) {
        return categoryUpdater.update(
                categoryReader.findByCategoryId(input.productId()),
                Category.createBuilder()
                        .topCategory(input.topCategory())
                        .normalCategory(input.normalCategory())
                        .build()
        );
    }

    public Category delete(DeleteCategoryInput input) {
        return categoryDeleter.delete(
                categoryReader.findByCategoryId(
                        input.categoryId()
                )
        );
    }

}
