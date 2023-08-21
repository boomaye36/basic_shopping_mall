package com.shopping.shopping_mall.dto;

import com.shopping.shopping_mall.domain.Users;
import lombok.Getter;

@Getter
public class SessionUser {
    private String name;
    private String email;
    private String profile_yn;

    public SessionUser(Users user){
        this.name = user.getName();
        this.email = user.getEmail();

    }
}
