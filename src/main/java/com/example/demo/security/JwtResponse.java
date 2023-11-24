package com.example.demo.security;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class JwtResponse {
    private final String type = "Bearer";
    private String token;
}
