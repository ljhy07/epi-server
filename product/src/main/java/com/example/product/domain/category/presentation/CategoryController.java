package com.example.product.domain.category.presentation;

import com.example.product.domain.category.domain.Category;
import com.example.product.domain.category.presentation.dto.req.CreateCategoryInput;
import com.example.product.domain.category.presentation.dto.req.DeleteCategoryInput;
import com.example.product.domain.category.presentation.dto.req.QueryCategoryInput;
import com.example.product.domain.category.presentation.dto.req.UpdateCategoryInput;
import com.example.product.domain.category.service.MutationCategoryService;
import com.example.product.domain.category.service.QueryCategoryService;
import com.example.product.domain.product.domain.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class CategoryController {

    private final MutationCategoryService mutationCategoryService;
    private final QueryCategoryService queryCategoryService;

    @MutationMapping
    public Category createCategory(
            @Argument(name = "createCategoryInput")
            CreateCategoryInput createCategoryInput
    ){
        return mutationCategoryService.create(createCategoryInput);
    }

    @MutationMapping
    public Category updateCategory(
            @Argument(name = "updateCategoryInput")
            UpdateCategoryInput updateCategoryInput
    ){
        return mutationCategoryService.update(updateCategoryInput);
    }

    @MutationMapping
    public Category deleteCategory(
            @Argument(name = "deleteCategoryInput")
            DeleteCategoryInput deleteCategoryInput
    ){
        return mutationCategoryService.delete(deleteCategoryInput);
    }

    @QueryMapping
    public Category getCategoryById(
            @Argument(name = "queryCategoryInput")
            QueryCategoryInput queryCategoryInput
    ){
        return queryCategoryService.getCategoryById(queryCategoryInput);
    }

    @QueryMapping
    public Category getCategoryByProduct(
            @Argument(name = "queryCategoryInput")
            QueryCategoryInput queryCategoryInput
    ){
        return queryCategoryService.getCategoryByProduct(queryCategoryInput);
    }

    @QueryMapping
    public List<Category> getAllCategories(
            @Argument(name = "queryCategoryInput")
            QueryCategoryInput queryCategoryInput
    ){
        return queryCategoryService.getCategoriesByTopCategory(queryCategoryInput);
    }

    @QueryMapping
    public List<Product> getAllProductsByCategory(
            @Argument(name = "queryCategoryInput")
            QueryCategoryInput queryCategoryInput
    ){
        return queryCategoryService.getProductsByCategory(queryCategoryInput);
    }

}
