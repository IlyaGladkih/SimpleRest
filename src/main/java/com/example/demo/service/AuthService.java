package com.example.demo.service;

import com.example.demo.models.User;
import com.example.demo.security.JwtProvider;
import com.example.demo.security.JwtResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AuthService {

    private UserService userService;
    public JwtProvider jwtProvider;

    @Autowired
    public AuthService(UserService userService, JwtProvider jwtProvider) {
        this.userService = userService;
        this.jwtProvider = jwtProvider;
    }

    public JwtResponse getNewToken(User user){
        return new JwtResponse(jwtProvider.generateToken(user));
    }
}
