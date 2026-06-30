package com.example.music.app.Controller;

import com.example.music.app.Entity.User;
import com.example.music.app.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdminController {
    @Autowired
    private UserService userService;
    @PostMapping("/register")
     public User postUser(@RequestBody User user){
         return userService.saveNewUser(user);
     }
}
