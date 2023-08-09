package com.shopping.shopping_mall.dto;

import com.shopping.shopping_mall.domain.Address;
import com.shopping.shopping_mall.domain.Order;
import com.shopping.shopping_mall.domain.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class OrderDto {
    private Long orderId;
    private String userName;
    private LocalDateTime orderDate;
    private OrderStatus orderStatus;
    private Address address;
    private List<OrderItemDto> orderItems;
    private int totalPrice;
    public OrderDto(Order order){
        orderId = order.getId();
        userName = order.getUsers().getName();
        orderDate = order.getOrderDate();
        orderStatus  = order.getStatus();
        address = order.getDelivery().getAddress();
        orderItems = order.getOrderItems().stream()
                .map(orderItem -> new OrderItemDto(orderItem))
                .collect(Collectors.toList());
        totalPrice = order.getTotalPrice();
    }
}
