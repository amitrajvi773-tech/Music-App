package com.example.music.app.Controller;

import com.example.music.app.Entity.User;
import com.example.music.app.JWT.JwtUtilis;
import com.example.music.app.Security.SecurityServiceImp;
import com.example.music.app.Service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/public")
public class publicController {

    @Autowired
    private UserService userService;

    @Autowired
    private SecurityServiceImp securityServiceImp;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtUtilis jwtUtilis;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/register")
    public User register(@Valid @RequestBody User user) {
        return userService.saveNewUser(user);

    }
    @PostMapping("/login")
    public ResponseEntity<String> login(@Valid @RequestBody User user) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(),user.getPassword()));
        String token=jwtUtilis.generateToken(user.getUsername());
        return ResponseEntity.ok(token);
    }
}
