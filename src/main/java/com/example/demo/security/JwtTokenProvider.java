package com.example.demo.security;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class JwtTokenProvider {
    // Very simple, non-secure token structure for tests: base64 of "id|email|role1,role2"

    public String createToken(Long userId, String email, Set<String> roles) {
        if (roles == null) roles = Collections.emptySet();
        String data = (userId == null ? 0L : userId) + "|" + (email == null ? "" : email) + "|" + String.join(",", roles);
        return Base64.getEncoder().encodeToString(data.getBytes(StandardCharsets.UTF_8));
    }

    public boolean validateToken(String token) {
        try {
            String decoded = new String(Base64.getDecoder().decode(token), StandardCharsets.UTF_8);
            return decoded.split("\\|").length >= 3;
        } catch (Exception e) {
            return false;
        }
    }

    public String getEmail(String token) {
        try {
            String decoded = new String(Base64.getDecoder().decode(token), StandardCharsets.UTF_8);
            String[] parts = decoded.split("\\|");
            return parts.length >= 2 ? parts[1] : null;
        } catch (Exception e) {
            return null;
        }
    }

    public Set<String> getRoles(String token) {
        try {
            String decoded = new String(Base64.getDecoder().decode(token), StandardCharsets.UTF_8);
            String[] parts = decoded.split("\\|");
            if (parts.length >= 3 && !parts[2].isEmpty()) {
                String[] rs = parts[2].split(",");
                Set<String> out = new HashSet<>();
                for (String r : rs) out.add(r);
                return out;
            }
            return new HashSet<>();
        } catch (Exception e) {
            return new HashSet<>();
        }
    }

    public Long getUserId(String token) {
        try {
            String decoded = new String(Base64.getDecoder().decode(token), StandardCharsets.UTF_8);
            String[] parts = decoded.split("\\|");
            return Long.valueOf(parts[0]);
        } catch (Exception e) {
            return null;
        }
    }
}
