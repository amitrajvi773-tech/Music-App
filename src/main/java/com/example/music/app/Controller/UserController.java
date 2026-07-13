package com.example.music.app.Controller;

import com.example.music.app.DTO.UserResponseDTO;
import com.example.music.app.Entity.User;
import com.example.music.app.Service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import jakarta.persistence.EntityNotFoundException;

@RestController
@Tag(name = "User Controller" , description = "User Management")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDTO> getUser(@PathVariable long id){
         UserResponseDTO user=userService.findById(id);
         if (user == null) {
             throw new EntityNotFoundException("User not found");
         }
         return ResponseEntity.ok(user);
     }


    @DeleteMapping
    public void deleteById(){
         Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
         String username=authentication.getName();
         User user=userService.findByUsername(username);
        if (user == null) {
            throw new EntityNotFoundException("User not found");
        }
         userService.deleteByid(user.getId());
    }

    @PutMapping
    public User updateUser(@RequestBody User user){
         Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
        String username=authentication.getName();
        User update=userService.findByUsername(username);
         if(update==null){
             throw new UsernameNotFoundException("User not found in putmapping");
         }
         update.setEmail(user.getEmail());
         update.setUsername(user.getUsername());
         update.setPassword(user.getPassword());
         return userService.saveUser(update);

    }

}
