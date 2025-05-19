package com.example.mixit.api.controller;

import com.example.mixit.api.model.User;
import com.example.mixit.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RestController
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public User getUser(@RequestParam int id) {
        Optional user = userService.getUser(id);
        if(user.isPresent()) {
            return (User) user.get();
        }
        return null;
    }

    @PostMapping
    public User setUser(@RequestBody int id) {
        
        return null;
    }
}
