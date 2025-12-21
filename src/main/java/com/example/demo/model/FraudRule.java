package com.example.demo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "fraud_rules", uniqueConstraints = @UniqueConstraint(columnNames = "ruleCode"))
public class FraudRule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false, unique = true)
    private String ruleCode;

    @NotBlank
    private String ruleType;

    private String description;

    private boolean active = true;

    private LocalDateTime createdAt;

    @PrePersist
    public void onCreate() {
        createdAt = LocalDateTime.now();
    }

    public FraudRule() {}

    public Long getId() { return id; }
    public String getRuleCode() { return ruleCode; }
    public String getRuleType() { return ruleType; }
    public String getDescription() { return description; }
    public boolean isActive() { return active; }

    public void setId(Long id) { this.id = id; }
    public void setRuleCode(String ruleCode) { this.ruleCode = ruleCode; }
    public void setRuleType(String ruleType) { this.ruleType = ruleType; }
    public void setDescription(String description) { this.description = description; }
    public void setActive(boolean active) { this.active = active; }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private final FraudRule r = new FraudRule();

        public Builder id(Long id) { r.setId(id); return this; }
        public Builder ruleCode(String c) { r.setRuleCode(c); return this; }
        public Builder ruleType(String t) { r.setRuleType(t); return this; }
        public Builder description(String d) { r.setDescription(d); return this; }
        public Builder active(boolean a) { r.setActive(a); return this; }

        public FraudRule build() { return r; }
    }
}