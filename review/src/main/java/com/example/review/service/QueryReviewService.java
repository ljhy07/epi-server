package com.example.review.service;

import com.example.review.domain.Review;
import com.example.review.presentation.dto.req.ReviewInput;
import com.example.review.service.implementation.ReviewReader;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class QueryReviewService {

    private final ReviewReader reviewReader;

    public Review getReviewById(ReviewInput reviewInput) {
        return reviewReader.findByReviewId(reviewInput.reviewId());
    }

    public List<Review> getReviewByUser(ReviewInput reviewInput) {
        Long userId = null;
        return reviewReader.findByUser(userId);
    }

    public List<Review> getReviewByProduct(ReviewInput reviewInput) {
        return reviewReader.findByProduct(reviewInput.productId());
    }

}
