package com.shopping.shopping_mall.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private String name;
    @Data
    public static class CreateUserRequest{
        private String userLoginId;
        private String password;
        private String name;
        private String PhoneNumber;

        //  private String userId;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CreateUserResponse{
        private Long id;
    }
}
