package com.example.product.product.presentation;

import com.example.product.product.domain.Product;
import com.example.product.product.presentation.dto.req.CreateProductInput;
import com.example.product.product.presentation.dto.req.DeleteProductInput;
import com.example.product.product.presentation.dto.req.QueryProductInput;
import com.example.product.product.presentation.dto.req.UpdateProductInput;
import com.example.product.product.service.MutationProductService;
import com.example.product.product.service.QueryProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ProductController {

    private final MutationProductService mutationProductService;
    private final QueryProductService queryProductService;

    @QueryMapping
    public Product getProduct(
            @Argument(name = "queryProductInput")
            QueryProductInput queryProductInput
    ) {
        return queryProductService.getProduct(queryProductInput);
    }

    @QueryMapping
    public List<Product> getProducts(
            @Argument(name = "queryProductInput")
            QueryProductInput queryProductInput
    ) {
        return queryProductService.getProducts(queryProductInput);
    }

    @QueryMapping
    public List<Product> getProductsByProductName(
            @Argument(name = "queryProductInput")
            QueryProductInput queryProductInput
    ) {
        return queryProductService.getProductsByProductName(queryProductInput);
    }

    @MutationMapping
    public Product createProduct(
            @Argument(name = "createProductInput")
            CreateProductInput createProductInput
    ) {
        return mutationProductService.create(createProductInput);
    }

    @MutationMapping
    public Product updateProduct(
            @Argument(name = "updateProductInput")
            UpdateProductInput updateProductInput
    ) {
        return mutationProductService.update(updateProductInput);
    }

    @MutationMapping
    public Product deleteProduct(
            @Argument(name = "deleteProductInput")
            DeleteProductInput deleteProductInput
    ) {
        return mutationProductService.delete(deleteProductInput);
    }

}
