package com.example.demo.http.controllers;

import com.example.demo.models.User;
import com.example.demo.security.JwtResponse;
import com.example.demo.security.Role;
import com.example.demo.service.AuthService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RestController
@RequestMapping("/hello")
public class HelloController {

    private UserService userService;
    private AuthService authService;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public HelloController(UserService userService, AuthService authService,PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.authService = authService;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping
    public ResponseEntity<JwtResponse> getToken(@RequestParam("username") String name, @RequestParam("password") String psw){
        User user = User.builder().login(name).password(passwordEncoder.encode(psw)).role(Role.USER).build();
        userService.saveUser(user);
        return ResponseEntity.ok(authService.getNewToken(user));
    }
}
