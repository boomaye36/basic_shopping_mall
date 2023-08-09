package com.shopping.shopping_mall.service;

import com.shopping.shopping_mall.domain.*;
import com.shopping.shopping_mall.repository.ItemRepository;
import com.shopping.shopping_mall.repository.OrderRepository;
import com.shopping.shopping_mall.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final ItemRepository itemRepository;
    //주문
    @Transactional
    public Long order(Long userId, Long itemId, int count){

        //엔티티 조회
        Users user = userRepository.findUserById(userId);
        Item item = itemRepository.findItemById(itemId);

        //배송정보 생성
        Delivery delivery = new Delivery();
        delivery.setAddress(user.getAddress());

        //주문 상품 생성
        OrderItem orderItem = OrderItem.createOrderitem(item, item.getPrice(), count);

        //주문 생성
        Order order = Order.createOrder(user, delivery, orderItem);

        //주문 저장
        orderRepository.save(order);

        return order.getId();
    }
}
