package com.example.demo.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "fraud_alert_records")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FraudAlertRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long claimId; // FK reference

    private String serialNumber;

    private String alertType;

    @Enumerated(EnumType.STRING)
    private Severity severity;

    private String message;

    private LocalDateTime alertDate;

    private Boolean resolved = false;

    @PrePersist
    protected void onCreate() {
        this.alertDate = LocalDateTime.now();
        if (resolved == null) {
            resolved = false;
        }
    }

    public enum Severity {
        LOW,
        MEDIUM,
        HIGH,
        CRITICAL
    }
}