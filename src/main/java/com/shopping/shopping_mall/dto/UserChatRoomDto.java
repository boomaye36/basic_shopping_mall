package com.shopping.shopping_mall.dto;

import com.shopping.shopping_mall.api.UserApiController;
import com.shopping.shopping_mall.domain.Chat;
import com.shopping.shopping_mall.domain.UserChatRoom;
import com.shopping.shopping_mall.domain.Users;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserChatRoomDto {
    //private List<Users> users;
    private String chatName;
   // private int cnt;

//    public UserChatRoomDto(UserChatRoom userChatRoom){
//        userName = userChatRoom.getUser().getName();
//        cnt = userChatRoom.getCnt();
//    }
//    public UserChatRoomDto(UserChatRoom userChatRoom){
//        this.users = userChatRoom.getUser().getUserChatRooms()
//                .stream()
//                .map(UserChatRoom::getUser)
//                .collect(Collectors.toList());
//        this.chatName = userChatRoom.getChat().getRoomName();
//        this.cnt = userChatRoom.getChat().getCnt();
//    }
}
