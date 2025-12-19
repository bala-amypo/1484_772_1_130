package com.example.demo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;

@Entity
@Table(
        name = "fraud_rules",
        uniqueConstraints = @UniqueConstraint(columnNames = "ruleCode")
)
public class FraudRule {

    /* ================= PRIMARY KEY ================= */

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /* ================= FIELDS ================= */

    @NotBlank
    @Column(nullable = false, unique = true)
    private String ruleCode;

    @NotBlank
    @Column(nullable = false)
    private String ruleType;

    @Column(nullable = true)   // optional
    private String description;

    @Column(nullable = false)
    private Boolean active = true;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    /* ================= CONSTRUCTORS ================= */

    // No-args constructor
    public FraudRule() {
        this.active = true;
    }

    // Core-fields constructor
    public FraudRule(String ruleCode, String ruleType, String description) {
        this.ruleCode = ruleCode;
        this.ruleType = ruleType;
        this.description = description;
        this.active = true;
    }

    /* ================= LIFECYCLE ================= */

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
        if (this.active == null) {
            this.active = true;
        }
    }

    /* ================= GETTERS & SETTERS ================= */

    public Long getId() {
        return id;
    }

    public String getRuleCode() {
        return ruleCode;
    }

    public void setRuleCode(String ruleCode) {
        this.ruleCode = ruleCode;
    }

    public String getRuleType() {
        return ruleType;
    }

    public void setRuleType(String ruleType) {
        this.ruleType = ruleType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean isActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}
