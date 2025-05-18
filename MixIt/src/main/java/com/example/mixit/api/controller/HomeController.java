package com.example.mixit.api.controller;

import com.example.mixit.api.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
    @RequestMapping("/")
    public String index() {
        return "index.html";
    }

    @RequestMapping("/about")
    public String about() {
        return "about.html";
    }

    @RequestMapping("/login")
    public String login() {
        return "login.html";
    }
    @RequestMapping("/search")
    public String search() {
        return "search.html";
    }
    @RequestMapping("/register")
    public String register( ) {

        return "register.html";
    }
    @RequestMapping("/account")
    public String account(Model model) {

        return "account.html";
    }
}
