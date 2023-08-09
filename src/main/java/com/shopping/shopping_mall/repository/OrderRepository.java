package com.shopping.shopping_mall.repository;

import com.shopping.shopping_mall.domain.Order;
import com.shopping.shopping_mall.domain.OrderStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    @Query("SELECT DISTINCT o FROM Order o" +
            " JOIN FETCH o.users u" +
            " JOIN FETCH o.delivery d" +
            " JOIN FETCH o.orderItems oi" +
            " JOIN FETCH oi.item i")
    Page<Order> findAllWithItem(Pageable pageable);
   Optional<Order> findById(Long id);

    @Query("SELECT DISTINCT o FROM Order o" +
            " JOIN FETCH o.users m" +
            " JOIN FETCH o.delivery d" +
            " JOIN FETCH o.orderItems oi" +
            " JOIN FETCH oi.item i" +
            " WHERE (:status IS NULL OR o.status = :status)" +
            " AND (:name IS NULL OR m.name LIKE %:name%)")
    List<Order> findAllBySearch(OrderStatus status, String name);
}
