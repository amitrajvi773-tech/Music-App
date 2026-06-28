package com.example.music.app.Security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SpringSecurity {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http)throws Exception {
        return http.authorizeHttpRequests(auth -> auth.
                        requestMatchers("/public/", "/public/**").permitAll().
                        requestMatchers("/user/", "/user/**").authenticated().
                        requestMatchers("/admin/**").hasRole("ADMIN").
                        anyRequest().permitAll()).
                        httpBasic(Customizer.withDefaults()).
                        csrf(AbstractHttpConfigurer::disable).
                        build();
    }


    @Bean
    public PasswordEncoder passwordEncoder(){
            return new BCryptPasswordEncoder();
        }








}

