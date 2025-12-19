package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.request.LoginRequest;
import com.example.demo.request.RegisterRequest;
import com.example.demo.security.JwtTokenProvider;
import com.example.demo.service.UserService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;
    private final JwtTokenProvider jwtTokenProvider;

    public AuthController(
            UserService userService,
            JwtTokenProvider jwtTokenProvider
    ) {
        this.userService = userService;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    /* ================= REGISTER ================= */

    @PostMapping("/register")
    public ResponseEntity<User> register(
            @RequestBody RegisterRequest request
    ) {
        User user = userService.registerUser(request);
        return ResponseEntity.ok(user);
    }

    /* ================= LOGIN ================= */

    @PostMapping("/login")
    public ResponseEntity<String> login(
            @RequestBody LoginRequest request
    ) {
        User user = userService.loginUser(request);
        String token = jwtTokenProvider.generateToken(user.getEmail());
        return ResponseEntity.ok(token);
    }
}
