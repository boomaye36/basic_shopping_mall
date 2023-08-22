package com.shopping.shopping_mall.service;

import com.shopping.shopping_mall.domain.Review;
import com.shopping.shopping_mall.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ReviewService {
    private final ReviewRepository reviewRepository;
    @Transactional
    public String createReview(Review review){
        reviewRepository.save(review);
        return review.getTitle();
    }
}
