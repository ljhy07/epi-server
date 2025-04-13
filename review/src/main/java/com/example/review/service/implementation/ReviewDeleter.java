package com.example.review.service.implementation;

import com.example.review.domain.Review;
import com.example.review.domain.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReviewDeleter {

    private final ReviewRepository reviewRepository;

    public Review delete(Review review) {
        reviewRepository.delete(review);
        return review;
    }
}
