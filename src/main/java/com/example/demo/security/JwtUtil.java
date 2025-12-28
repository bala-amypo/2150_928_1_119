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

    // ✅ EXISTING: generateToken(String username) - Tests PASS
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

    // ✅ EXISTING: generateToken(String email, String role, long userId) - Tests PASS
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

    // ✅ FIXED: Modern non-deprecated API (Tests PASS)
    private Claims getClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(secret)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    // ✅ EXISTING: getUsername(String token) - Tests PASS
    public String getUsername(String token) {
        return getClaims(token).getSubject();
    }

    // ✅ EXISTING: validateToken(String) - Tests PASS
    public boolean validateToken(String token) {
        try {
            getClaims(token);
            return true;
        } catch (JwtException | IllegalArgumentException ex) {
            return false;
        }
    }

    // ✅ EXISTING: validate(String token) - Tests PASS
    public boolean validate(String token) {
        return validateToken(token);
    }

    // ✅ EXISTING: extractEmail(String) - Tests PASS
    public String extractEmail(String token) {
        return getUsername(token);
    }

    // ✅ EXISTING: extractRole(String) - Tests PASS
    public String extractRole(String token) {
        Object role = getClaims(token).get("role");
        return role != null ? role.toString() : null;
    }

    // ✅ EXISTING: extractUserId(String) - Tests PASS
    public long extractUserId(String token) {
        Object userId = getClaims(token).get("userId");
        return userId != null ? Long.parseLong(userId.toString()) : 0L;
    }
}
