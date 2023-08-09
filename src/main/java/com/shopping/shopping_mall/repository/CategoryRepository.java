package com.shopping.shopping_mall.repository;

import com.shopping.shopping_mall.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    List<Category> findByIdIn(List<Long> ids);
}
