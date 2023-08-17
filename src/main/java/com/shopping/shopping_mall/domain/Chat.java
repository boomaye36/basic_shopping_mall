package com.shopping.shopping_mall.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity @Getter @Setter
@EntityListeners(AuditingEntityListener.class)

public class Chat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String roomId;

    private String roomName;

    private int cnt;
    @OneToMany(mappedBy = "chat")
    private List<UserChatRoom> userChatRooms = new ArrayList<>();


    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;

    public UserChatRoom addUserToChat(Users user) {
        UserChatRoom userChatRoom = new UserChatRoom();
        userChatRoom.setChat(this);
        userChatRoom.setUser(user);
        userChatRooms.add(userChatRoom);

        cnt++;
        return userChatRoom;
    }
}
