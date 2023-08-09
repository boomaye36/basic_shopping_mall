package com.shopping.shopping_mall.repository;

import com.shopping.shopping_mall.domain.Item;
import com.shopping.shopping_mall.domain.Nice;
import com.shopping.shopping_mall.domain.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LikeRepository extends JpaRepository<Nice, Long> {
    Optional<Nice> findByUsersAndItem(Users user, Item item);
}
