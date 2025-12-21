package com.example.demo.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(
    name = "fraud_rules",
    uniqueConstraints = @UniqueConstraint(columnNames = "ruleCode")
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FraudRule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String ruleCode;

    private String ruleType;
    private String description;

    private Boolean active = true;

    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
        if (active == null) {
            active = true;
        }
    }
}