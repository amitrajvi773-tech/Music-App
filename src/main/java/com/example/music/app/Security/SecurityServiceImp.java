package com.example.music.app.Security;

import com.example.music.app.Repository.UserRepository;
import com.example.music.app.Entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
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
            return org.springframework.security.core.userdetails.User.builder().username(user.getUsername()).
                    password(user.getPassword())
                    .roles(user.getUserrole().toArray(new String[0])).
                     build();
    }
    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration configuration)
            throws Exception {

        return configuration.getAuthenticationManager();
    }





 }

