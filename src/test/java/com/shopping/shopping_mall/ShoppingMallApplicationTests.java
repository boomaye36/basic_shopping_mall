package com.shopping.shopping_mall;

import com.shopping.shopping_mall.api.ChatController;
import com.shopping.shopping_mall.domain.Chat;
import com.shopping.shopping_mall.domain.UserChatRoom;
import com.shopping.shopping_mall.domain.Users;
import com.shopping.shopping_mall.dto.ChatRoomDto;
import com.shopping.shopping_mall.dto.UserChatRoomDto;
import com.shopping.shopping_mall.repository.ChatRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)

class ShoppingMallApplicationTests {
    @InjectMocks
    private ChatController chatController;

    @Mock
    private ChatRepository chatRepository;
    @Test
    void contextLoads() {
        Chat chat1 = new Chat();
        chat1.setId(1L);
        chat1.setRoomId("room1");
        chat1.setRoomName("Chat Room 1");

        Users user1 = new Users();
        user1.setId(1L);
        user1.setName("유저1");

        Chat chat2 = new Chat();
        chat2.setId(2L);
        chat2.setRoomId("room2");
        chat2.setRoomName("Chat Room 2");
        Users user2 = new Users();
        user2.setId(2L);
        user2.setName("유저2");
       // UserChatRoom
        // Mock the behavior of chatRepository
//        when(chatRepository.findAllWithUser()).thenReturn(Arrays.asList(chat1, chat2));
//
//        // Call the method being tested
//        //List<ChatRoomDto> chatRoomDtoList = chatController.findAllRoom();
//
//        // Verify the results
//        Assertions.assertEquals(2, chatRoomDtoList.size());

//        ChatRoomDto chatRoomDto1 = chatRoomDtoList.get(0);
//        assertEquals(chat1.getId(), chatRoomDto1.getId());
//        assertEquals(chat1.getRoomId(), chatRoomDto1.getRoomId());
//        assertEquals(chat1.getRoomName(), chatRoomDto1.getRoomName());
//        ChatRoomDto chatRoomDto2 = chatRoomDtoList.get(1);
//        assertEquals(chat2.getId(), chatRoomDto2.getId());
//        assertEquals(chat2.getRoomId(), chatRoomDto2.getRoomId());
//        assertEquals(chat2.getRoomName(), chatRoomDto2.getRoomName());

        // Verify that chatRepository.findAllWithUser() was called
       //verify(chatRepository, times(1)).findAllWithUser();
    }

}
