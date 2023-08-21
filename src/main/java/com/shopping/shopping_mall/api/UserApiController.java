package com.shopping.shopping_mall.api;

import com.shopping.shopping_mall.domain.Users;
import com.shopping.shopping_mall.dto.UserDto;
import com.shopping.shopping_mall.repository.UserRepository;
import com.shopping.shopping_mall.service.UserService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.hibernate.sql.Delete;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor

public class UserApiController {
    private final UserService userService;
    private final UserRepository userRepository;

    @PostMapping("/user")
    public com.shopping.shopping_mall.dto.UserDto.CreateUserResponse saveUser(@RequestBody @Valid com.shopping.shopping_mall.dto.UserDto.CreateUserRequest createUserRequest){
        Users users = new Users();
        users.setName(createUserRequest.getName());
        users.setUserLoginId(createUserRequest.getUserLoginId());
        users.setPassword(createUserRequest.getPassword());
        users.setPhoneNumber(createUserRequest.getPhoneNumber());
       // users.setUserId(createUserRequest.getUserId());
        Long id = userService.join(users);
        return new com.shopping.shopping_mall.dto.UserDto.CreateUserResponse(id);
    }

    @GetMapping("/users")
    public Result users(){
        List<Users> findUsers = userService.findUsers();
        List<UserDto> collect = new ArrayList<>();
        for (Users users : findUsers){
            collect.add(new UserDto(users.getName()));
        }
        return new Result(collect, collect.size());
    }
    @PutMapping("user/{id}")
    public UpdateUserResponse updateUser(@PathVariable("id") Long id,
                                         @RequestBody @Valid UpdateUserRequest request){
        userService.updateUserName(id, request.getName());
        Users findUser = userService.findOne(id);
        return new UpdateUserResponse(findUser.getId(), findUser.getName());
    }
    @DeleteMapping("user/{id}")
    public void deleteUser(@PathVariable("id") Long id){
        userRepository.deleteById(id);
    }

    @GetMapping("/login")
    public String login(){
        return "index";
    }
    @Data
    @AllArgsConstructor
    public static class Result<T>{
        private T data;
        private int count;
    }
    @Data
    @AllArgsConstructor
    static class UserDto{
        private String name;
    }
    @Data
    static class UpdateUserRequest {
        @NotEmpty
        private String name;
    }
    @Data
    @AllArgsConstructor
    static class UpdateUserResponse {
        private Long id;
        private String name;
    }
    @Data
    @AllArgsConstructor
    static class DeleteUserRequest{
        @NotEmpty
        private String name;
    }
    @Data
    @AllArgsConstructor
    static class DeleteUserResponse {
        private Long id;
        private String name;
    }
}
