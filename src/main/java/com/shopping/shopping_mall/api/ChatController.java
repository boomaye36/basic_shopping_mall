package com.shopping.shopping_mall.api;

import com.shopping.shopping_mall.domain.Chat;
import com.shopping.shopping_mall.dto.ChatRoom;
import com.shopping.shopping_mall.repository.ChatRepository;
import com.shopping.shopping_mall.service.ChatService;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping
    public List<ChatRoom> findAllRoom(){
        return chatService.findAllRoom();
    }
}
