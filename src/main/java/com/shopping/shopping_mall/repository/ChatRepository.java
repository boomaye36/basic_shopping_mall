package com.shopping.shopping_mall.repository;

import com.shopping.shopping_mall.domain.Chat;
import com.shopping.shopping_mall.domain.UserChatRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ChatRepository extends JpaRepository<Chat, Long> {
    boolean existsByRoomName(String roomName);

//    @Query("SELECT DISTINCT c FROM Chat c" +
//            " JOIN FETCH c.userChatRooms uc" +
//            " Join FETCH uc.chat " +
//            " JOIN FETCH uc.user" +
//            " JOIN FETCH uc.cnt")
//    List<Chat> findAllWithUser();

    @Query("SELECT ucr FROM UserChatRoom ucr" +
            " JOIN FETCH ucr.user" +
            " JOIN FETCH ucr.chat")
    List<UserChatRoom> findAllWithUsersAndChats();

    Chat findByRoomName(String roomName);
}
