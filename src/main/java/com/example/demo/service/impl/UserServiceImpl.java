package com.example.demo.service.impl;

import com.example.demo.exception.BadRequestException;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.request.LoginRequest;
import com.example.demo.request.RegisterRequest;
import com.example.demo.security.JwtTokenProvider;
import com.example.demo.service.UserService;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    public UserServiceImpl(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder,
            JwtTokenProvider jwtTokenProvider
    ) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    /* ================= REGISTER USER ================= */

    @Override
    public User registerUser(RegisterRequest request) {

        if (userRepository.existsByEmail(request.getEmail())) {
            throw new BadRequestException("Email already in use");
        }

        User user = new User(
                request.getName(),
                request.getEmail(),
                passwordEncoder.encode(request.getPassword()),
                Set.of("USER")
        );

        return userRepository.save(user);
    }

    /* ================= LOGIN USER ================= */

    @Override
    public User loginUser(LoginRequest request) {

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() ->
                        new NoSuchElementException("User not found")
                );

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new BadRequestException("Invalid input");
        }

        // Token generation happens here (if required by controller)
        jwtTokenProvider.generateToken(user.getEmail());

        return user;
    }

    /* ================= FIND BY ID ================= */

    @Override
    public User getById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() ->
                        new NoSuchElementException("User not found")
                );
    }

    /* ================= FIND BY EMAIL ================= */

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new NoSuchElementException("User not found")
                );
    }
}
