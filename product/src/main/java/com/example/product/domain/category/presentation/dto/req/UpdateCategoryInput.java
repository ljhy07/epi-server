package com.example.product.domain.category.presentation.dto.req;

import com.example.product.domain.category.domain.value.TopCategory;

public record UpdateCategoryInput(
        Long productId,
        TopCategory topCategory,
        String normalCategory
) {
}
