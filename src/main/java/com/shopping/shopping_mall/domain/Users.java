package com.shopping.shopping_mall.domain;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
@EntityListeners(AuditingEntityListener.class)

public class Users {
    @Id
    @GeneratedValue
    @Column(name="user_id")

    private Long id;

//    @NotEmpty
//    private String userId;

//    @NotEmpty
//    private String password;

    @NotEmpty
    private String name;
    @Embedded
    private Address address;

    private String phoneNumber;

    @Enumerated(EnumType.STRING)
    private Grade grade;

    private Integer point;

    @OneToMany(mappedBy = "users")
    private List<Order> orders = new ArrayList<>();

    @OneToMany(mappedBy = "users")
    private List<Nice> nices = new ArrayList<>();

    @OneToMany(mappedBy = "users")
    private List<Payment> payments = new ArrayList<>();

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;

}
