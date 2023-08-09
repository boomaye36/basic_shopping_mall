package com.shopping.shopping_mall.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Getter @Setter
@EntityListeners(AuditingEntityListener.class)

public class Payment {
    @Id @GeneratedValue
    @Column(name = "payment_id")
    private Long id;

    @NotNull
    private String name;

    @NotNull
    private int point;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id")
    private Users users;



}
