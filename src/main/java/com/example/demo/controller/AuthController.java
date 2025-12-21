package com.example.demo.controller;

import com.example.demo.dto.AuthRequest;
import com.example.demo.dto.RegisterRequest;
import com.example.demo.model.User;
import com.example.demo.service.UserService;
import org.springframework.http.ResponseEntity;

public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    public ResponseEntity<?> register(RegisterRequest request) {
        return userService.register(request);
    }

    public ResponseEntity<?> login(AuthRequest request) {
        return userService.login(request);
    }
}