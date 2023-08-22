package com.shopping.shopping_mall.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class OrderItem {

    @Id
    @GeneratedValue
    @Column(name = "order_item_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    @JsonIgnore
    private Order order;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "coupon_id")
    private Coupon coupon;
    private int orderPrice;

    private int count;

    protected OrderItem(){

    }

    public static OrderItem createOrderitem(Item item, int orderPrice, int count){
        OrderItem orderItem = new OrderItem();
        orderItem.setItem(item);
        orderItem.setOrderPrice(orderPrice);
        orderItem.setCount(count);

        item.removeStock(count);
        return orderItem;

    }
    public void applyCoupon(Coupon coupon) {
        this.coupon = coupon;
    }
    public void cancel(){
        getItem().addStock(count);
    }

    public int getTotalPrice(){
        if (coupon != null){
            System.out.println("쿠폰있어요");
            return (int)(getOrderPrice() * getCount() - getOrderPrice() * getCoupon().getPercent());
        }
        return  getOrderPrice() * getCount();
    }


}
