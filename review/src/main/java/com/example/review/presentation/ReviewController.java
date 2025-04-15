package com.example.review.presentation;

import com.example.review.domain.Review;
import com.example.review.presentation.dto.req.ReviewInput;
import com.example.review.service.MutationReviewService;
import com.example.review.service.QueryReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ReviewController {

    private final MutationReviewService mutationReviewService;
    private final QueryReviewService queryReviewService;

    @MutationMapping
    public Review createReview(
            @Argument(name = "reviewInput")
            ReviewInput reviewInput
    ) {
        return mutationReviewService.create(reviewInput);
    }

    @MutationMapping
    public Review updateReview(
            @Argument(name = "reviewInput")
            ReviewInput reviewInput
    ) {
        return mutationReviewService.update(reviewInput);
    }

    @MutationMapping
    public Review deleteReview(
            @Argument(name = "reviewInput")
            ReviewInput reviewInput
    ) {
        return mutationReviewService.delete(reviewInput);
    }

    @QueryMapping
    public Review getReviewById(
            @Argument(name = "reviewInput")
            ReviewInput reviewInput
    ) {
        return queryReviewService.getReviewById(reviewInput);
    }

    @QueryMapping
    public List<Review> getReviewByUser(
            @Argument(name = "reviewInput")
            ReviewInput reviewInput
    ) {
        return queryReviewService.getReviewByUser(reviewInput);
    }

    @QueryMapping
    public List<Review> getReviewByProduct(
            @Argument(name = "reviewInput")
            ReviewInput reviewInput
    ) {
        return queryReviewService.getReviewByProduct(reviewInput);
    }

}
