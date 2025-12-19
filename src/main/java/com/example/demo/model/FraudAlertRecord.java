package com.example.demo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

@Entity
@Table(name = "fraud_alert_records")
public class FraudAlertRecord {

    /* ================= PRIMARY KEY ================= */

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /* ================= FIELDS ================= */

    @NotNull
    @Column(nullable = false)
    private Long claimId;

    @NotBlank
    @Column(nullable = false)
    private String serialNumber;

    @NotBlank
    @Column(nullable = false)
    private String alertType;

    @NotBlank
    @Column(nullable = false)
    private String severity; // LOW / MEDIUM / HIGH / CRITICAL

    @Column(nullable = true)
    private String message;

    @Column(nullable = false, updatable = false)
    private LocalDateTime alertDate;

    @Column(nullable = false)
    private Boolean resolved = false;

    /* ================= RELATIONSHIPS ================= */

    @ManyToOne(optional = false)
    @JoinColumn(name = "claim_fk", nullable = false)
    private WarrantyClaimRecord claim;

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    /* ================= CONSTRUCTORS ================= */

    // No-args constructor
    public FraudAlertRecord() {
        this.resolved = false;
    }

    // Core-fields constructor
    public FraudAlertRecord(
            WarrantyClaimRecord claim,
            User user,
            String alertType,
            String severity,
            String message
    ) {
        this.claim = claim;
        this.user = user;
        this.claimId = claim.getId();
        this.serialNumber = claim.getSerialNumber();
        this.alertType = alertType;
        this.severity = severity;
        this.message = message;
        this.resolved = false;
    }

    /* ================= LIFECYCLE ================= */

    @PrePersist
    protected void onCreate() {
        this.alertDate = LocalDateTime.now();
        if (this.resolved == null) {
            this.resolved = false;
        }
    }

    /* ================= GETTERS & SETTERS ================= */

    public Long getId() {
        return id;
    }

    public Long getClaimId() {
        return claimId;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public String getAlertType() {
        return alertType;
    }

    public void setAlertType(String alertType) {
        this.alertType = alertType;
    }

    public String getSeverity() {
        return severity;
    }

    public void setSeverity(String severity) {
        this.severity = severity;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDateTime getAlertDate() {
        return alertDate;
    }

    public Boolean getResolved() {
        return resolved;
    }

    public void setResolved(Boolean resolved) {
        this.resolved = resolved;
    }

    public WarrantyClaimRecord getClaim() {
        return claim;
    }

    public void setClaim(WarrantyClaimRecord claim) {
        this.claim = claim;
        if (claim != null) {
            this.claimId = claim.getId();
            this.serialNumber = claim.getSerialNumber();
        }
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
