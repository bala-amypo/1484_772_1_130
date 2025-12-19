package com.example.demo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(
        name = "users",
        uniqueConstraints = @UniqueConstraint(columnNames = "email")
)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /* ================= FIELDS ================= */

    @NotBlank
    @Column(nullable = false)
    private String name;

    @NotBlank
    @Email
    @Column(nullable = false, unique = true)
    private String email;

    @NotBlank
    @Column(nullable = false)
    private String password; // stored ONLY as BCrypt hash

    @NotEmpty
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id")
    )
    @Column(name = "role")
    private Set<String> roles = new HashSet<>();

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    /* ================= RELATIONSHIPS ================= */

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<FraudAlertRecord> fraudAlerts;

    /* ================= CONSTRUCTORS ================= */

    // No-args constructor
    public User() {
        this.roles.add("USER"); // default role
    }

    // Core-fields constructor
    public User(String name, String email, String rawPassword, Set<String> roles) {
        this.name = name;
        this.email = email;
        setPassword(rawPassword); // ensures hashing
        if (roles == null || roles.isEmpty()) {
            this.roles.add("USER");
        } else {
            this.roles = roles;
        }
    }

    /* ================= LIFECYCLE ================= */

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
        if (this.roles == null || this.roles.isEmpty()) {
            this.roles = new HashSet<>();
            this.roles.add("USER");
        }
    }

    /* ================= BUSINESS RULES ================= */

    // Password is ALWAYS stored hashed
    public void setPassword(String rawPassword) {
        this.password = new BCryptPasswordEncoder().encode(rawPassword);
    }

    /* ================= GETTERS ================= */

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public Set<String> getRoles() {
        return roles;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}
