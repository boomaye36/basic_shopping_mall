package com.shopping.shopping_mall.domain;

import com.shopping.shopping_mall.exception.NotEnoughStockException;
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

public class Item {

    @Id
    @GeneratedValue
    @Column(name = "item_id")
    private Long id;

    @NotNull
    private String name;

    @NotNull
    private int price;

    private String desc;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "seller_id")
    private Seller seller;

    private String img;

    private int stockQuantity;

    private int likeCnt;
//    @ManyToOne(fetch = FetchType.LAZY)
//    private Category category;
    @ManyToMany
    @JoinTable(name = "item_category",
                joinColumns = @JoinColumn(name = "item_id"),
                inverseJoinColumns = @JoinColumn(name = "category_id"))
    private List<Category> categories = new ArrayList<>();

    @OneToMany(mappedBy = "item")
    private List<Review> reviews = new ArrayList<>();

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "item")
    private List<Nice> nices = new ArrayList<>();

    public void  addStock(int quantity){
        this.stockQuantity += quantity;
    }

    public void removeStock(int quantity){
        int restStock = this.stockQuantity - quantity;
        if (restStock < 0) throw new NotEnoughStockException("need more stock");
        this.stockQuantity = restStock;
    }

    public int addLike(Users user){
        if (nices.stream().noneMatch(nice -> nice.getUsers().equals(user))){
            Nice nice = new Nice();
            nice.setUsers(user);
            nice.setItem(this);
            nices.add(nice);
            likeCnt++;
        }
        return likeCnt;
    }
    public int removeLike(Users user){
        nices.removeIf(nice -> nice.getUsers().equals(user));
        if (likeCnt > 0) likeCnt--;
        return likeCnt;
    }

}
