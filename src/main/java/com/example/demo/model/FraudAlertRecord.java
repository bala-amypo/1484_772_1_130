package com.example.demo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "fraud_alert_records")
public class FraudAlertRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String alertType;

    @NotBlank
    private String severity;

    private String message;

    private LocalDateTime alertDate;

    private boolean resolved = false;

    @ManyToOne
    @JoinColumn(name = "claim_id", nullable = false)
    private WarrantyClaimRecord claim;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @PrePersist
    public void onCreate() {
        alertDate = LocalDateTime.now();
    }

    public FraudAlertRecord() {}

    public Long getId() { return id; }
    public String getAlertType() { return alertType; }
    public String getSeverity() { return severity; }
    public String getMessage() { return message; }
    public boolean isResolved() { return resolved; }
    public WarrantyClaimRecord getClaim() { return claim; }

    public String getSerialNumber() {
        return claim != null ? claim.getSerialNumber() : null;
    }

    public void setId(Long id) { this.id = id; }
    public void setAlertType(String alertType) { this.alertType = alertType; }
    public void setSeverity(String severity) { this.severity = severity; }
    public void setMessage(String message) { this.message = message; }
    public void setResolved(boolean resolved) { this.resolved = resolved; }
    public void setClaim(WarrantyClaimRecord claim) { this.claim = claim; }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private final FraudAlertRecord f = new FraudAlertRecord();

        public Builder id(Long id) { f.id = id; return this; }
        public Builder alertType(String alertType) { f.alertType = alertType; return this; }
        public Builder severity(String severity) { f.severity = severity; return this; }
        public Builder message(String message) { f.message = message; return this; }
        public Builder resolved(boolean resolved) { f.resolved = resolved; return this; }
        public Builder claim(WarrantyClaimRecord claim) { f.claim = claim; return this; }
        public Builder user(User user) { f.user = user; return this; }

        public FraudAlertRecord build() {
            return f;
        }
    }
}