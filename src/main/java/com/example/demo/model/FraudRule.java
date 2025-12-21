package com.example.demo.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(
        name = "fraud_rules",
        uniqueConstraints = @UniqueConstraint(columnNames = "ruleCode")
)
@Getter
@Setter
@NoArgsConstructor
public class FraudRule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String ruleCode;

    @Column(nullable = false)
    private String ruleType;

    private String description;

    private Boolean active = true;

    private LocalDateTime createdAt;

    public FraudRule(String ruleCode, String ruleType) {
        this.ruleCode = ruleCode;
        this.ruleType = ruleType;
        this.active = true;
    }

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
        if (active == null) {
            active = true;
        }
    }
}