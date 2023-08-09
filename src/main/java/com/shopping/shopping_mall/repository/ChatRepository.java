package com.shopping.shopping_mall.repository;

import com.shopping.shopping_mall.domain.Chat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatRepository extends JpaRepository<Chat, Long> {
    boolean existsByRoomName(String roomName);

}
