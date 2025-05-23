package com.example.mixit.service;

import com.example.mixit.api.model.User;
import com.example.mixit.api.repo.UserRepository;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserService {

    private UserRepository repository;

    public void UserController(UserRepository repository) {
        this.repository = repository;
    }

    @PostMapping
    public User newUser(@RequestBody User user) {
        return repository.save(user);
    }
}
