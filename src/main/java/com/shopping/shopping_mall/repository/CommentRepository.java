package com.shopping.shopping_mall.repository;

import com.shopping.shopping_mall.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {

}
