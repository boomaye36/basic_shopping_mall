package com.shopping.shopping_mall.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.shopping.shopping_mall.domain.Chat;
import com.shopping.shopping_mall.domain.UserChatRoom;
import com.shopping.shopping_mall.domain.Users;
import com.shopping.shopping_mall.dto.ChatMessage;
import com.shopping.shopping_mall.dto.ChatRoom;
import com.shopping.shopping_mall.dto.ChatRoomDto;
import com.shopping.shopping_mall.dto.UserChatRoomDto;
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
import java.util.stream.Collectors;

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

//        UserChatRoom userChatRoom = new UserChatRoom();
//
//        userChatRoom.addMember(user, chat);
//        chat.getUserChatRooms().add(userChatRoom);
        UserChatRoom userChatRoom = chat.addUserToChat(user);
        chatRoomRepository.save(userChatRoom);
       //System.out.println("roomList : " + chat.getRoomName());
        chatRepository.save(chat);

        return chat;
    }

    @Transactional
    public Chat joinRoom(String roomName, Long userId){
        if (!chatRepository.existsByRoomName(roomName)){
            throw new ChatRoomNameAlreadyExistsException("검색하신 채팅방이 없습니다.");
        }

        Chat chat = chatRepository.findByRoomName(roomName);
        Users user = userRepository.findUserById(userId);
        if (chatRoomRepository.existsByUserAndChat(user, chat)){
            throw new RuntimeException("이미 채팅방에 참여중입니다.");
        }
        UserChatRoom userChatRoom = chat.addUserToChat(user);


        //userChatRoom.addMember(user, chat);
        //chat.getUserChatRooms().add(userChatRoom);
        chatRoomRepository.save(userChatRoom);
        chatRepository.save(chat);
        return chat;
    }

    public List<ChatRoomDto> getAllUserChatRooms(){
        List<Chat> userChatRooms = chatRepository.findAll();
        return userChatRooms.stream()
                .map(this::convertToChatRoomDto)
                .collect(Collectors.toList());    }

    private ChatRoomDto convertToChatRoomDto(Chat chat) {
        List<UserChatRoomDto> usersInChat = chat.getUserChatRooms().stream()
                .map(userChatRoom -> new UserChatRoomDto(userChatRoom.getUser().getName())) // Assuming getUsername method exists
                .collect(Collectors.toList());

        return new ChatRoomDto(chat.getRoomName(), chat.getCnt(), usersInChat);
    }
    public <T> void sendMessage(WebSocketSession session, T message) {
        try {
            session.sendMessage(new TextMessage(objectMapper.writeValueAsString(message)));
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
    }
}
