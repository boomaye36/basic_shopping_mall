package com.shopping.shopping_mall.repository;

import com.shopping.shopping_mall.domain.Coupon;
import com.shopping.shopping_mall.domain.CouponState;
import com.shopping.shopping_mall.domain.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CouponRepository extends JpaRepository<Coupon, Long> {
    boolean existsByUsers(Users user);

    Optional<Coupon> findByUsersAndCouponState(Users user, CouponState selected);
}
