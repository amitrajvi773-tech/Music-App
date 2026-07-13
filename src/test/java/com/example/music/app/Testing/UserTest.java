package com.example.music.app.Testing;

import com.example.music.app.Entity.User;
import com.example.music.app.Repository.UserRepository;
import com.example.music.app.Service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class UserTest {
@Autowired
    UserRepository userRepository;
@Autowired
    UserService userService;

    @Test
    void testSaveUser() {

        User user = new User();
        user.setUsername("amitpratap");
        user.setEmail("amit51@gmail.com");
        user.setPassword("amitpratap");

        User savedUser = userService.saveUser(user);

        assertNotNull(savedUser);
    }

}
