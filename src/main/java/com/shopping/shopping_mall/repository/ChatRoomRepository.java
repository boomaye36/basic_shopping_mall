package com.shopping.shopping_mall.repository;

import com.shopping.shopping_mall.domain.Chat;
import com.shopping.shopping_mall.domain.UserChatRoom;
import com.shopping.shopping_mall.domain.Users;
import com.shopping.shopping_mall.dto.ChatRoom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatRoomRepository extends JpaRepository<UserChatRoom, Long> {
    boolean existsByUserAndChat(Users user, Chat chat);
}
