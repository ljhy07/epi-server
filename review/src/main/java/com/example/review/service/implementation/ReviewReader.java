package com.example.review.service.implementation;

import com.example.review.domain.Review;
import com.example.review.domain.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewReader {

    private final ReviewRepository reviewRepository;

    public Review findByReviewId(Long reviewId) {
        return reviewRepository.findByReviewId(reviewId);
    }

    public List<Review> findByProduct(Long productId) {
        return reviewRepository.findAllByProductId(productId);
    }

    public List<Review> findByUser(Long userId) {
        return reviewRepository.findAllByUserId(userId);
    }

}
