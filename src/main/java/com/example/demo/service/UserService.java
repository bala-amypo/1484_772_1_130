package com.example.demo.service;

import com.example.demo.model.User;

public interface UserService {

    User registerUser(User user);

    User loginUser(String email, String password);

    User getById(Long id);

    User findByEmail(String email);
}