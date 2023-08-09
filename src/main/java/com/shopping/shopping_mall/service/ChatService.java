package com.shopping.shopping_mall.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.shopping.shopping_mall.domain.Chat;
import com.shopping.shopping_mall.domain.UserChatRoom;
import com.shopping.shopping_mall.domain.Users;
import com.shopping.shopping_mall.dto.ChatMessage;
import com.shopping.shopping_mall.dto.ChatRoom;
import com.shopping.shopping_mall.exception.ChatRoomNameAlreadyExistsException;
import com.shopping.shopping_mall.repository.ChatRepository;
import com.shopping.shopping_mall.repository.ChatRoomRepository;
import com.shopping.shopping_mall.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;
import java.util.*;

@Slf4j
@RequiredArgsConstructor
@Service
public class ChatService {
    private final ObjectMapper objectMapper;
    private Map<String, ChatRoom> chatRooms;
    private final UserRepository userRepository;
    private final ChatRepository chatRepository;
    private final ChatRoomRepository chatRoomRepository;
    @PostConstruct
    private void init() {
        chatRooms = new LinkedHashMap<>();
    }

    public List<ChatRoom> findAllRoom() {
        return new ArrayList<>(chatRooms.values());
    }

    public ChatRoom findRoomById(String roomId) {
        return chatRooms.get(roomId);
    }

    //    public ChatRoom createRoom(String roomName, User user){
//        String randomId = UUID.randomUUID().toString();
//        Chat chat = new Chat();
//        //chat.setRoomId(randomId);
//        ChatRoom chatRoom = ChatRoom.builder()
//                .roomId(randomId)
//                .name(roomName)
//                .users(Collections.singleton((Users) user))
//                .build();
//        chatRooms.put(randomId, chatRoom);
//        chatRepository.save(chat);
//        return chatRoom;
//
//    }
    @Transactional
    public Chat createRoom(String roomName, Long userId) {
        if (chatRepository.existsByRoomName(roomName)) {
            throw new ChatRoomNameAlreadyExistsException("Chat room with the name " + roomName + " already exists.");
        }
        String randomId = UUID.randomUUID().toString();

        Users user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found with ID: " + userId));

        Chat chat = new Chat();
        chat.setRoomId(randomId);
        chat.setRoomName(roomName);

        UserChatRoom userChatRoom = new UserChatRoom();
        userChatRoom.setUser(user);
        userChatRoom.setChat(chat);


        chat.getUserChatRooms().add(userChatRoom);
       // chatRoomRepository.save(userChatRoom);
       //System.out.println("roomList : " + chat.getRoomName());
        chatRepository.save(chat);

        return chat;
    }

    public <T> void sendMessage(WebSocketSession session, T message) {
        try {
            session.sendMessage(new TextMessage(objectMapper.writeValueAsString(message)));
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
    }
}
