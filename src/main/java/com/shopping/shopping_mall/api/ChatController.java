package com.shopping.shopping_mall.api;

import com.shopping.shopping_mall.domain.Chat;
import com.shopping.shopping_mall.domain.UserChatRoom;
import com.shopping.shopping_mall.dto.ChatRoom;
import com.shopping.shopping_mall.dto.ChatRoomDto;
import com.shopping.shopping_mall.dto.UserChatRoomDto;
import com.shopping.shopping_mall.repository.ChatRepository;
import com.shopping.shopping_mall.service.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/chat")
public class ChatController {

    private final ChatService chatService;
    private final ChatRepository chatRepository;
    @PostMapping
    public Chat createRoom(@RequestParam String roomName,@RequestParam Long userId){

        return chatService.createRoom(roomName, userId);
    }

    @GetMapping("/find")
    public List<ChatRoomDto> findAllRoom(){

        return chatService.getAllUserChatRooms();
    }

    @PostMapping("/join")
    public Chat joinRoom(@RequestParam String roomName, @RequestParam Long userId){
        return chatService.joinRoom(roomName, userId);
    }
}
