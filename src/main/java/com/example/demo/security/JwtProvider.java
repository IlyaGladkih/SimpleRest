package com.example.demo.security;

import com.example.demo.models.User;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Component
public class JwtProvider {

    private SecretKey jwtkey;

    public JwtProvider(@Value("${jwt.secret.key}") String key) {
        this.jwtkey = Keys.hmacShaKeyFor(Decoders.BASE64.decode(key));
    }

    public String generateToken(User user){
        LocalDateTime now = LocalDateTime.now();
        Instant expiresInstant = now.plusDays(10).atZone(ZoneId.systemDefault()).toInstant();
        Date expires = Date.from(expiresInstant);
        return Jwts.builder()
                .setSubject(user.getLogin())
                .setExpiration(expires)
                .signWith(jwtkey)
                .claim("roles", user.getRole())
                .claim("login", user.getLogin())
                .compact();

    }

    public boolean validateToken(String token){
        try{
            Jwts.parserBuilder()
                    .setSigningKey(jwtkey)
                    .build()
                    .parseClaimsJws(token);
            return true;
        }catch (ExpiredJwtException expEx) {
            System.out.println("Token expired");

        } catch (UnsupportedJwtException unsEx) {
            System.out.println("Unsupported jwt");
        } catch (MalformedJwtException mjEx) {
            System.out.println("Malformed jwt");
        } catch (SignatureException sEx) {
            System.out.println("Invalid signature");
        } catch (Exception e) {
            System.out.println("invalid token");
        }
        return false;
    }

    public Claims getClaims(String token){
        return Jwts.parserBuilder()
                .setSigningKey(jwtkey)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}
