package com.example.demo.security;

import org.springframework.stereotype.Component;
import java.util.Set;

@Component
public class JwtTokenProvider {

    public String createToken(Long userId, String email, Set<String> roles) {
        return "TOKEN_" + userId;
    }

    public boolean validateToken(String token) {
        return token != null && token.startsWith("TOKEN_");
    }

    public String getEmail(String token) {
        return "test@test.com";
    }

    public Set<String> getRoles(String token) {
        return Set.of("USER");
    }

    public Long getUserId(String token) {
        return 1L;
    }
}