package com.example.Board.Service;

import com.example.Board.entity.User;
import com.example.Board.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService{
    private final UserRepository userRepository;
    private int nextId;
    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
        this.nextId = 1;
    }
    public User createUser(String username, String password) { //회원가입
        User user = new User(nextId++, username, password);
        userRepository.save(user);
        return user;
    }
    public User login(String username, String password) { //로그인
        List<User> userList = userRepository.findByUsernameAndPassword(username, password);
        if (userList.size() > 0) {
            return userList.get(0);
        } else {
            return null;
        }
    }







}