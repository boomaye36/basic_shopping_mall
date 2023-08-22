package com.shopping.shopping_mall.repository;

import com.shopping.shopping_mall.domain.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    List<Review> findByItem_Name(String name);

    Review findReviewById(Long id);
}
