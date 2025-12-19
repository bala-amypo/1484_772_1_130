package com.example.demo.service;

import com.example.demo.model.User;
import com.example.demo.request.LoginRequest;
import com.example.demo.request.RegisterRequest;

public interface UserService {

    User registerUser(RegisterRequest request);

    User loginUser(LoginRequest request);

    User getById(Long id);

    User findByEmail(String email);
}
