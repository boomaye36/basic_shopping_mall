package com.shopping.shopping_mall.repository;

import com.shopping.shopping_mall.domain.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<Users, Long> {

    List<Users> findByName(String name);

    @Override
    void deleteById(Long id);

    Users findUserById(Long userId);
}

