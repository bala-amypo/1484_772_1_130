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

    // 🔧 REQUIRED GETTERS / SETTERS
    public String getRuleCode() { return ruleCode; }
    public void setRuleCode(String ruleCode) { this.ruleCode = ruleCode; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getRuleType() { return ruleType; }
    public void setRuleType(String ruleType) { this.ruleType = ruleType; }

    public boolean isActive() { return active; }
    public void setActive(boolean active) { this.active = active; }
}
