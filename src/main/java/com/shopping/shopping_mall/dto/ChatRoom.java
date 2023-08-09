package com.shopping.shopping_mall.dto;

import com.shopping.shopping_mall.domain.Users;
import com.shopping.shopping_mall.service.ChatService;
import lombok.Builder;
import lombok.Getter;
import org.springframework.web.socket.WebSocketSession;

import java.util.HashSet;
import java.util.Set;
@Getter
public class ChatRoom {
    private String roomId;
    private String name;
    private Set<WebSocketSession> sessions = new HashSet<>();
    private Set<Users> users = new HashSet<>(); // 사용자 정보 저장

    @Builder
    public ChatRoom(String roomId, String name, Set<Users> users){
        this.roomId = roomId;
        this.name = name;
        this.users = users;
    }

    public void handlerActions(WebSocketSession session, ChatMessage chatMessage, ChatService chatService){
        boolean flag = true;
        if (chatMessage.getType().equals(ChatMessage.MessageType.ENTER)){
            sessions.add(session);
            chatMessage.setMessage(chatMessage.getSender() + "님이 입장했습니다.");
            chatMessage.setType(ChatMessage.MessageType.TALK);
        }
        sendMessage(chatMessage, chatService);
    }
    private<T> void sendMessage(T message, ChatService chatService){
        sessions.parallelStream()
                .forEach(session -> chatService.sendMessage(session, message));
    }
}
