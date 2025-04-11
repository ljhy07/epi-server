package com.example.review.domain.repository;

import com.example.review.domain.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    Review findByReviewId(Long reviewId);

    List<Review> findAllByProductId(Long productId);

    List<Review> findAllByUserId(Long userId);
}
