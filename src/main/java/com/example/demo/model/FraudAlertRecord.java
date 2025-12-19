package com.example.demo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "fraud_alert_record")
public class FraudAlertRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String alertType;

    @NotBlank
    private String severity;

    @NotBlank
    private String message;

    private LocalDateTime alertDate;
    private boolean resolved = false;

    @ManyToOne
    @JoinColumn(name = "claim_id", nullable = false)
    private WarrantyClaimRecord claim;

    @PrePersist
    public void onCreate() {
        this.alertDate = LocalDateTime.now();
    }

    public FraudAlertRecord() {}

    // 🔧 REQUIRED GETTERS / SETTERS
    public void setAlertType(String alertType) { this.alertType = alertType; }
    public void setSeverity(String severity) { this.severity = severity; }
    public void setMessage(String message) { this.message = message; }

    public void setResolved(boolean resolved) { this.resolved = resolved; }
}
