package com.example.demo.service;

import com.example.demo.model.User;
import java.util.Optional;

public interface UserService {
    User registerUser(User user);
    User loginUser(String email, String password);
    User getById(Long id);
    Optional<User> findByEmail(String email);
}