package com.example.review.domain.repository;

import com.example.review.domain.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
    Review findByReviewId(Long reviewId);

    List<Review> findAllByProductId(Long productId);

    List<Review> findAllByUserId(Long userId);
}
