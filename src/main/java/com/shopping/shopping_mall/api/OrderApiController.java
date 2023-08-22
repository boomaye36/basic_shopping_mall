package com.shopping.shopping_mall.api;

import ch.qos.logback.core.status.Status;
import com.shopping.shopping_mall.domain.Order;
import com.shopping.shopping_mall.domain.OrderItem;
import com.shopping.shopping_mall.domain.OrderStatus;
import com.shopping.shopping_mall.dto.OrderDto;
import com.shopping.shopping_mall.dto.OrderSearchDto;
import com.shopping.shopping_mall.repository.OrderRepository;
import com.shopping.shopping_mall.service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class OrderApiController {

    private final OrderRepository orderRepository;
    private final OrderService orderService;
    @GetMapping("/orders")
    public List<OrderDto> orders(){
        Pageable pageable = PageRequest.of(0, 10);

        Page<Order> ordersPage = orderRepository.findAllWithItem(pageable);
        List<Order> orders = ordersPage.getContent(); // The list of orders for the requested page

        List<OrderDto> result = new ArrayList<>();
        for (Order o : orders) result.add(new OrderDto(o));
//        for (Order o : orders) {
//            // Apply coupon to each order item and calculate total price
//            for (OrderItem orderItem : o.getOrderItems()) {
//                orderItem.applyCoupon(orderService.findCouponForUser(o.getUsers())); // Apply coupon to order item
//                // Update the order item's total price
//                orderItem.setOrderPrice(orderItem.getTotalPrice());
//            }
//
//            result.add(new OrderDto(o));
//        }
        return result;

    }

    @GetMapping("/orders/search")
    public List<OrderDto> orders(@RequestBody OrderSearchDto orderSearchDto){

        List<Order> searchOrder = orderRepository.findAllBySearch(orderSearchDto.getOrderStatus(),orderSearchDto.getUserName());
        List<OrderDto> result = new ArrayList<>();
        for (Order o : searchOrder) result.add(new OrderDto(o));
        return result;
    }
}
