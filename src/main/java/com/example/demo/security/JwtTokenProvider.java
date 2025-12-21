package com.example.demo.security;

public class JwtTokenProvider {

    public String generateToken(String email) {
        return "test-jwt-token";
    }

    public String getUsernameFromToken(String token) {
        return "test@example.com";
    }

    public boolean validateToken(String token) {
        return true;
    }
}