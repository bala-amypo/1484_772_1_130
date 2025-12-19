package com.example.demo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table(
    name = "fraud_rule",
    uniqueConstraints = @UniqueConstraint(columnNames = "ruleCode")
)
public class FraudRule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false, unique = true)
    private String ruleCode;

    @NotBlank
    private String description;

    @NotBlank
    private String ruleType;

    private boolean active = true;

    public FraudRule() {}

    // getters & setters
}
