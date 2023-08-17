package com.shopping.shopping_mall.dto;

import com.shopping.shopping_mall.domain.Chat;
import com.shopping.shopping_mall.domain.UserChatRoom;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChatRoomDto {
    private String roomName;
    private int cnt;
    private List<UserChatRoomDto> userChatRooms;

//    public ChatRoomDto(Chat chat){
//        roomName = chat.getRoomName();
//        userChatRooms = chat.getUserChatRooms().stream()
//                .map(userChatRoom -> new UserChatRoomDto(userChatRoom))
//                .collect(Collectors.toList());
//    }
}
