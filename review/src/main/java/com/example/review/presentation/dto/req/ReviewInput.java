package com.example.review.presentation.dto.req;

public record ReviewInput (
        Long reviewId,
        Long productId,
        Integer rating,
        String comment
){
}
