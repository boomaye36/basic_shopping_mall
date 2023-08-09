package com.shopping.shopping_mall.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
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

public class Seller {
    @Id
    @GeneratedValue
    @Column(name="seller_id")
    private Long id;

    @NotNull
    private String name;

    @NotNull
    private String phoneNo;

    private String img;

    private String desc;

    @Embedded
    private Address address;

    @JsonIgnore
    @OneToMany(mappedBy = "seller")
    private List<Item> items = new ArrayList<>();

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;
}
