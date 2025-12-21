package com.example.demo.repository;

import com.example.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    // Finds user by email
    Optional<User> findByEmail(String email);

    // Check for duplicate email
    boolean existsByEmail(String email);

    // Retrieves all users
    List<User> findAll();
}