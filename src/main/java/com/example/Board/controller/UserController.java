package com.example.Board.controller;


import com.example.Board.Service.UserService;
import com.example.Board.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    private final UserService userService;
    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }
    @PostMapping("/api/signup")
    public User createUser(@RequestParam String username, @RequestParam String password){
        return userService.createUser(username, password);
    }
    @PostMapping("/api/login")
    public User login(@RequestParam String username, @RequestParam String password){
        return userService.login(username,password);
    }
}
