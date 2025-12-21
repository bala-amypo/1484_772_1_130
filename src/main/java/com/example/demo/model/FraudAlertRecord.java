package com.example.demo.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "fraud_alert_records")
@Getter
@Setter
@NoArgsConstructor
public class FraudAlertRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long claimId;

    @Column(nullable = false)
    private String serialNumber;

    @Column(nullable = false)
    private String alertType;

    @Column(nullable = false)
    private String severity;

    private String message;

    private LocalDateTime alertDate;

    private Boolean resolved = false;

    @ManyToOne
    @JoinColumn(name = "claim_id_fk")
    private WarrantyClaimRecord claim;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public FraudAlertRecord(
            Long claimId,
            String serialNumber,
            String alertType,
            String severity
    ) {
        this.claimId = claimId;
        this.serialNumber = serialNumber;
        this.alertType = alertType;
        this.severity = severity;
        this.resolved = false;
    }

    @PrePersist
    protected void onCreate() {
        this.alertDate = LocalDateTime.now();
        if (resolved == null) {
            resolved = false;
        }
    }
}