package com.shopping.shopping_mall.repository;

import com.shopping.shopping_mall.domain.UserChatRoom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatRoomRepository extends JpaRepository<UserChatRoom, Long> {
}
