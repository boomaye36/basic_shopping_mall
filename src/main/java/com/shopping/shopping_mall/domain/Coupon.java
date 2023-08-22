package com.shopping.shopping_mall.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "coupon")
@EntityListeners(AuditingEntityListener.class)

public class Coupon {
    @Id
    @GeneratedValue
    @Column(name="coupon_id")
    private Long id;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users users;

    private float percent;

    @Enumerated
    private CouponState couponState;
    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;
}