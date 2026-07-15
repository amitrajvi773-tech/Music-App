package com.example.music.app.Testing;

import com.example.music.app.Entity.User;
import com.example.music.app.JWT.JwtUtilis;
import com.example.music.app.Service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class IntegrationServiceTest {
@Autowired
    JwtUtilis jwtUtilis;
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

    @Test
    void testDelete(){
        long id =5;
        userService.deleteByid(id);
    }

    @ParameterizedTest
    @CsvSource ({
        "amit"
    })
    void testGenrateToken(String username){
String token=jwtUtilis.generateToken(username);
assertNotNull(token);
    }



    @Test
    void testextractUsername(){
        String username = "testUser";

        String validToken = jwtUtilis.generateToken(username);

        String extracted = jwtUtilis.extractUsername(validToken);

        boolean checking =jwtUtilis.isTokenValid(validToken,username);
        boolean checkExpiry=jwtUtilis.isTokenExpired(validToken);
        assertEquals(username,extracted);
        assertTrue(checking);
        assertTrue(checkExpiry); // error come beacuse token is expired so this give false and error come
    }
}
