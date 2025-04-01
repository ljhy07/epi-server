package com.example.product.domain.category.domain.repository;

import com.example.product.domain.category.domain.Category;
import com.example.product.domain.category.domain.value.TopCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    Category findByCategoryId(Long categoryId);

    List<Category> findAllByTopCategory(TopCategory topCategory);

    Category findByProductId(Long productId);
}
