package com.shopping.shopping_mall.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.shopping.shopping_mall.dto.ChatRoom;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
public class UserChatRoom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @JsonIgnore

    private Users user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "chat_id")
    @JsonIgnore
    private Chat chat;

    private int cnt;

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;

    //    public static UserChatRoom createUserChat(Users user){
//        UserChatRoom userChatRoom = new UserChatRoom();
//        userChatRoom.setUser(user);
//        userChatRoom.setCnt(cnt);
//    }
    public void addMember(Users user, Chat chat) {
        this.setUser(user);
        this.setChat(chat);
        //cnt++;
    }
}
