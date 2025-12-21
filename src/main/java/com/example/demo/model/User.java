package com.example.demo.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(
    name = "users",
    uniqueConstraints = @UniqueConstraint(columnNames = "email")
)
@Getter
@Setter
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    // Hashed password only (BCrypt done in service layer)
    @Column(nullable = false)
    private String password;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(
        name = "user_roles",
        joinColumns = @JoinColumn(name = "user_id")
    )
    @Column(name = "role", nullable = false)
    private Set<String> roles = new HashSet<>();

    private LocalDateTime createdAt;

    // Relationship
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private Set<FraudAlertRecord> alerts = new HashSet<>();

    // Core fields constructor
    public User(String name, String email, String password, Set<String> roles) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.roles = (roles == null || roles.isEmpty())
                ? Set.of("USER")
                : roles;
    }

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
        if (roles == null || roles.isEmpty()) {
            this.roles = Set.of("USER");
        }
    }
}