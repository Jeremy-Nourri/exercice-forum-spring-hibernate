package com.example.forum.controller;

import com.example.forum.model.User;
import com.example.forum.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/signin")
    public String signIn(){
        return "signin";
    }

    @PostMapping("/signin")
    public String signIn(@ModelAttribute("user") User user, Model model){
        if(userService.signIn(user.getUsername(), user.getPassword())){
            User userFound = userService.getUserByUsername(user.getUsername());
            model.addAttribute("user", userFound);
            return "profile";
        }
        return "signin";
    }

    @GetMapping("/signup")
    public String signUp(){
        return "signup";
    }

    @PostMapping("/signup")
    public String signUp(@RequestParam String username, @RequestParam String password, Model model){
        if(userService.existsByUsername(username)){
            model.addAttribute("error", "Pseudo déjà utilisé");
            return "signup";
        }
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        userService.saveUser(user);
        return "signin";
    }







}
