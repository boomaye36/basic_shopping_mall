package com.shopping.shopping_mall.repository;

import com.shopping.shopping_mall.domain.Item;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ItemRepository extends JpaRepository<Item, Long> {
     Item findItemById(Long itemId);
     Optional<Item> findItemByName(String name);
    // Optional<Item> findById(Long itemId);
    //Page<Item> findAll
     @Query("SELECT i FROM Item i JOIN i.categories c WHERE c.id IN :categoryIds")
     Page<Item> findByCategoryIds(List<Long> categoryIds, Pageable pageable);
}
