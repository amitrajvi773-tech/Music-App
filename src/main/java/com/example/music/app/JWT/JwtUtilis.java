package com.example.music.app.JWT;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import javax.crypto.SecretKey;
import java.util.Date;

@Service
public class JwtUtilis {
    @Value("${jwt.secret}")
    String secretKey ;

    private SecretKey key;

    @PostConstruct
    public void init() {
        key = Keys.hmacShaKeyFor(secretKey.getBytes());
    }

    public String generateToken(String username){
     return Jwts.builder()
         .subject(username)
         .issuedAt(new Date())
         .expiration(new Date(System.currentTimeMillis()+ 1000 * 60 * 60))
         .signWith(key, SignatureAlgorithm.HS256)
         .compact();
    }

    public String extractUsername(String token){
        Claims claims = Jwts.parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(token)
                .getPayload();

        return claims.getSubject();

    }
    public boolean isTokenValid(String token , String username) {

        return extractUsername(token).equals(username)
                && !isTokenExpired(token);
    }

    public boolean isTokenExpired(String token) {

        Claims claims = Jwts.parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(token)
                .getPayload();

        return claims.getExpiration().before(new Date());
    }
}

