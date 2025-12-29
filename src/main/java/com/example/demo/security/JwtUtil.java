package com.example.demo.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.JwtException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtUtil {

    @Value("${jwt.secret:change-me-secret}")
    private String secret;

    @Value("${jwt.expiration:3600000}")
    private long validityInMs;

   
    public String generateToken(String username) {
        Date now = new Date();
        Date expiry = new Date(now.getTime() + validityInMs);

        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(now)
                .setExpiration(expiry)
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }

   
    public String generateToken(String email, String role, long userId) {
        Date now = new Date();
        Date expiry = new Date(now.getTime() + validityInMs);

        Map<String, Object> claims = new HashMap<>();
        claims.put("role", role);
        claims.put("userId", userId);

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(email)
                .setIssuedAt(now)
                .setExpiration(expiry)
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }


    private Claims getClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(secret)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    
    public String getUsername(String token) {
        return getClaims(token).getSubject();
    }

    
    public boolean validateToken(String token) {
        try {
            getClaims(token);
            return true;
        } catch (JwtException | IllegalArgumentException ex) {
            return false;
        }
    }

    
    public boolean validate(String token) {
        return validateToken(token);
    }

    
    public String extractEmail(String token) {
        return getUsername(token);
    }

    
    public String extractRole(String token) {
        Object role = getClaims(token).get("role");
        return role != null ? role.toString() : null;
    }

    
    public long extractUserId(String token) {
        Object userId = getClaims(token).get("userId");
        return userId != null ? Long.parseLong(userId.toString()) : 0L;
    }
}
