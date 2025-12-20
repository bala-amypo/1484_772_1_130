package com.example.demo.service.impl;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository repository;

    public UserServiceImpl(UserRepository repository) {
        this.repository = repository;
    }

    public User registerUser(User user) {
        if (repository.existsByEmail(user.getEmail())) {
            throw new IllegalArgumentException("Email already in use");
        }
        return repository.save(user);
    }

    public User loginUser(String email, String password) {
        User user = repository.findByEmail(email)
                .orElseThrow(() -> new NoSuchElementException("User not found"));
        if (!user.getPassword().equals(password)) {
            throw new IllegalArgumentException("Invalid input");
        }
        return user;
    }

    public User getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("User not found"));
    }

    public java.util.Optional<User> findByEmail(String email) {
        return repository.findByEmail(email);
    }
}