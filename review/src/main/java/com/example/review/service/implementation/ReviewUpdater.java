package com.example.review.service.implementation;

import com.example.review.domain.Review;
import com.example.review.domain.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReviewUpdater {

    private final ReviewRepository reviewRepository;

    public Review update(Review updatableReview, Review review) {
        updatableReview.updateBuilder()
                .rating(review.getRating())
                .comment(review.getComment())
                .build();

        return reviewRepository.save(updatableReview);
    }

}
