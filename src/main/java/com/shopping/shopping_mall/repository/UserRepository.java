package com.shopping.shopping_mall.repository;

import com.shopping.shopping_mall.domain.SocialType;
import com.shopping.shopping_mall.domain.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<Users, Long> {

    List<Users> findByName(String name);

    @Override
    void deleteById(Long id);

    Users findUserById(Long userId);

    Optional<Users> findByEmail(String email);

    Optional<Users> findByRefreshToken(String refreshToken);

    Optional<Users> findByNickname(String nickname);
    Optional<Users> findBySocialTypeAndSocialId(SocialType socialType, String socialId);
}

