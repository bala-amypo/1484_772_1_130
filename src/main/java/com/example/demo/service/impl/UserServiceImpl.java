package com.example.demo.service.impl;

import com.example.demo.dto.AuthRequest;
import com.example.demo.dto.RegisterRequest;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import java.util.NoSuchElementException;

public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User register(RegisterRequest request) {
        userRepository.findByEmail(request.getEmail())
                .ifPresent(u -> { throw new IllegalArgumentException(); });

        User user = User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(request.getPassword())
                .roles(request.getRoles())
                .build();

        return userRepository.save(user);
    }

    @Override
    public User login(AuthRequest request) {
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(NoSuchElementException::new);

        if (!user.getPassword().equals("ENC_" + request.getPassword())) {
            throw new IllegalArgumentException();
        }

        return user;
    }
}