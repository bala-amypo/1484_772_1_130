package com.example.demo.controller;

import com.example.demo.dto.AuthRequest;
import com.example.demo.dto.AuthResponse;
import com.example.demo.dto.RegisterRequest;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.security.JwtTokenProvider;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

public class AuthController {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    public AuthController(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtTokenProvider jwtTokenProvider) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    public ResponseEntity<?> register(RegisterRequest request) {
        Optional<User> existing = userRepository.findByEmail(request.getEmail());
        if (existing.isPresent()) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        User u = User.builder()
                .email(request.getEmail())
                .name(request.getName())
                .roles(request.getRoles())
                .password(passwordEncoder.encode(request.getPassword()))
                .build();
        u = userRepository.save(u);
        String token = jwtTokenProvider.createToken(u.getId() == null ? 0L : u.getId(), u.getEmail(), u.getRoles());
        return ResponseEntity.ok(new AuthResponse(token));
    }

    public ResponseEntity<?> login(AuthRequest request) {
        Optional<User> opt = userRepository.findByEmail(request.getEmail());
        if (opt.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        User u = opt.get();
        if (!passwordEncoder.matches(request.getPassword(), u.getPassword())) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        String token = jwtTokenProvider.createToken(u.getId() == null ? 0L : u.getId(), u.getEmail(), u.getRoles());
        return ResponseEntity.ok(new AuthResponse(token));
    }
}
