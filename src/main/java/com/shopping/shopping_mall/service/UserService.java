package com.shopping.shopping_mall.service;

import com.shopping.shopping_mall.domain.Users;
import com.shopping.shopping_mall.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public Long join(Users users){
        validDupicateUser(users);
        userRepository.save(users);
        return users.getId();
    }

    private void validDupicateUser(Users users){
        List<Users> findUsers = userRepository.findByName(users.getName());
        if (!findUsers.isEmpty()) throw new IllegalStateException("이미 존재하는 회원입니다.");
    }

    public List<Users> findUsers(){
        return userRepository.findAll();
    }

    public Users findOne(Long Id){
        return userRepository.findById(Id).orElse(null);
    }

    @Transactional
    public void updateUserName(Long id, String name){
        Users users = userRepository.findById(id).orElse(null);
        users.setName(name);
    }
    @Transactional
    public void deleteUserName(Long id, String name){
        Users users = userRepository.findById(id).orElse(null);
        userRepository.deleteById(users.getId());
    }

}
