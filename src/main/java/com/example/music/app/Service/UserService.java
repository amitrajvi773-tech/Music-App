package com.example.music.app.Service;

import com.example.music.app.DTO.UserResponseDTO;
import com.example.music.app.Entity.User;
import com.example.music.app.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Optional<User> findUserById(Long id){

       return userRepository.findById(id);
    }

    public User saveNewUser( User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setUserrole(new ArrayList<>(Arrays.asList("USER")));
        return userRepository.save(user);

    }
    public User saveUser(User updated){
        updated.setPassword(passwordEncoder.encode(updated.getPassword()));
        return userRepository.save(updated);
    }


    public void deleteByid(long myid) {
         userRepository.deleteById(myid);

    }

    public UserResponseDTO findById(long id){
        User user=  userRepository.findById(id).orElseThrow(()-> new UsernameNotFoundException("user not found"));
       return convertToDTO(user);
    }

    public User findByUsername(String username){

        return userRepository.findByUsername(username);
    }
    private UserResponseDTO convertToDTO(User user) {
        UserResponseDTO dto = new UserResponseDTO();
        dto.setUsername(user.getUsername());
        dto.setEmail(user.getEmail());
        return dto;
    }
}
