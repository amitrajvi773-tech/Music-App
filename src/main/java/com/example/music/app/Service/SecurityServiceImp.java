package com.example.music.app.Service;

import com.example.music.app.Repository.UserRepository;
import lombok.Data;
import com.example.music.app.Entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service

public class SecurityServiceImp implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
        User user= userRepository.findByUsername(username);
        if(user==null){
            throw new UsernameNotFoundException("user not found in loadUserByUsername");
        }
        if (user!=null){
            return org.springframework.security.core.userdetails.User.builder().username(user.getUsername()).
                    password(user.getPassword())
                    .roles(user.getUserrole().toArray(new String[0])).
                     build();
        }

        throw new UsernameNotFoundException("User not found"+username);

    }





 }

