package com.example.review.service;

import com.example.review.domain.Review;
import com.example.review.exception.UserNotCorrectException;
import com.example.review.presentation.dto.req.ReviewInput;
import com.example.review.service.implementation.ReviewCreator;
import com.example.review.service.implementation.ReviewDeleter;
import com.example.review.service.implementation.ReviewReader;
import com.example.review.service.implementation.ReviewUpdater;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MutationReviewService {

    private final ReviewCreator reviewCreator;
    private final ReviewUpdater reviewUpdater;
    private final ReviewDeleter reviewDeleter;
    private final ReviewReader reviewReader;

    public Review create(ReviewInput reviewInput) {
        Long userId = null;

        return reviewCreator.save(
                Review.createBuilder()
                        .productId(reviewInput.productId())
                        .userId(userId)
                        .rating(reviewInput.rating())
                        .comment(reviewInput.comment())
                        .build()
        );
    }

    public Review update(ReviewInput reviewInput) {
        Long userId = null;

        return reviewUpdater.update(
                reviewReader.findByReviewId(reviewInput.reviewId()),
                Review.createBuilder()
                        .rating(reviewInput.rating())
                        .comment(reviewInput.comment())
                        .build()
        );
    }

    public Review delete(ReviewInput reviewInput) {
        Long userId = null;
        Review review = reviewReader.findByReviewId(reviewInput.reviewId());

        if (!review.getUserId().equals(userId)) {
            throw new UserNotCorrectException();
        }

        return reviewDeleter.delete(review);
    }

}
