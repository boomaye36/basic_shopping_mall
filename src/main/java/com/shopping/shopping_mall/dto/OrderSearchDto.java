package com.shopping.shopping_mall.dto;

import com.shopping.shopping_mall.domain.Order;
import com.shopping.shopping_mall.domain.OrderStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderSearchDto {
    private String userName;
    private OrderStatus orderStatus;

//    public static class OrderSearchRequest{
//        private String name;
//        private OrderStatus orderStatus;
//    }
}
