package com.example.demo.util;

import com.example.demo.security.JwtAuthentification;
import com.example.demo.security.Role;
import io.jsonwebtoken.Claims;

import java.util.Collections;



public class JWTUtil {

    private JWTUtil() {
    }

    public static JwtAuthentification generate(Claims claims){
        JwtAuthentification authentification = new JwtAuthentification();
        authentification.setLogin(claims.get("login",String.class));
        authentification.setRoles(Collections.singleton(Role.valueOf(getRoles(claims))));
        return authentification;
    }

    public static String getRoles(Claims claims){
        return claims.get("roles", String.class);
    }
}
