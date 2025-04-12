package com.example.review.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity(name = "review")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Review {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reviewId;

    private Long productId;

    private Long userId;

    private Integer rating;

    private String comment;

    @Builder(builderMethodName = "createBuilder")
    public Review(Long productId, Long userId, Integer rating, String comment) {
        this.productId = productId;
        this.userId = userId;
        this.rating = rating;
        this.comment = comment;
    }

    @Builder(builderMethodName = "updateBuilder")
    public Review(Integer rating, String comment) {
        this.rating = rating;
        this.comment = comment;
    }

}
