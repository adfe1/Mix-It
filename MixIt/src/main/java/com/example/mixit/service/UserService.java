package com.example.mixit.service;

import com.example.mixit.api.model.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private List<User> userList;

    public UserService() {
        userList = new ArrayList<>();

        User user = new User(1,"lol","asdasd@.com", "askdjhgaj",50);
        userList.add(user);
    }

    public Optional<User> getUser(Integer id){
        Optional optional = Optional.empty();
        for(User user : userList){
            if(user.getId() == id){
                optional = Optional.of(user);
                return optional;
            }

        }
        return optional;
    }
}
