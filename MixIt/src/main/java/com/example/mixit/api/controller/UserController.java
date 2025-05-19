package com.example.mixit.api.controller;

import com.example.mixit.api.model.User;
import com.example.mixit.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


@RestController
public class UserController {

    private List<User> users = new ArrayList<>(User.testUser());

    @GetMapping("/users")
    public ResponseEntity<List<User>> allUsers() {
        return ResponseEntity.ok(this.users);
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<User> getUser(@PathVariable String id) {
        User user = this.findUserById(id);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(user);
    }

    @PostMapping("/users")
    public ResponseEntity<User> createUser(@RequestBody User user) throws URISyntaxException {
        User newUser = new User(
                UUID.randomUUID().toString(),
                user.name(),
                user.email(),
                user.age(),
                user.isVerified());
        this.users.add(newUser);
        return ResponseEntity.created(
                        new URI("/users/" + newUser.id()))
                .body(newUser);
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<User> updateUser(@PathVariable String id, @RequestBody User user) {
        User existingUser = this.findUserById(id);
        if (existingUser == null) {
            return ResponseEntity.notFound().build();
        }
        User updatedUser = new User(
                user.id(),
                user.name(),
                user.email(),
                user.age(),
                user.isVerified()
        );
        this.users.remove(existingUser);
        this.users.add(updatedUser);
        return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<User> deleteUser(@PathVariable String id) {
        User userToDelete = this.findUserById(id);
        if (userToDelete == null) {
            return ResponseEntity.notFound().build();
        }
        this.users.remove(userToDelete);
        return ResponseEntity.noContent().build();
    }

    private User findUserById(String id) {
        for (User user : users) {
            if (user.id().equals(id)) {
                return user;
            }
        }
        return null;
    }
}
