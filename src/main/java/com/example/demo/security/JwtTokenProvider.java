package com.example.demo.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.Set;

@Component
public class JwtTokenProvider {

    private final Key key =
            Keys.hmacShaKeyFor("jwt-secret-key-demo-1234567890123456".getBytes());

    private final long validity = 1000 * 60 * 60;

    public String createToken(Long userId, String email, Set<String> roles) {

        return Jwts.builder()
                .setSubject(email)               
                .claim("userId", userId)         
                .claim("roles", roles)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + validity))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    public Claims parseToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}
























// package com.example.demo.security;

// import java.util.Set;

// import org.springframework.stereotype.Component;

// @Component  
// public class JwtTokenProvider {

//     public String createToken(Long userId, String email, Set<String> roles) {
//         return userId + "|" + email + "|" + String.join(",", roles);
//     }

//     public boolean validateToken(String token) {
//         return token != null && token.contains("|");
//     }

//     public String getEmail(String token) {
//         return token.split("\\|")[1];
//     }

//     public Set<String> getRoles(String token) {
//         return Set.of(token.split("\\|")[2].split(","));
//     }

//     public Long getUserId(String token) {
//         return Long.valueOf(token.split("\\|")[0]);
//     }
// }