package com.example.demo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "warranty_claim_records")
public class WarrantyClaimRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String claimReason;

    @NotBlank
    private String claimantName;

    @Email
    private String claimantEmail;

    private String status = "PENDING";

    private LocalDateTime submittedAt;

    @ManyToOne
    @JoinColumn(name = "device_id", nullable = false)
    private DeviceOwnershipRecord device;

    @OneToMany(mappedBy = "claim", cascade = CascadeType.ALL)
    private java.util.List<FraudAlertRecord> alerts;

    @PrePersist
    public void onCreate() {
        submittedAt = LocalDateTime.now();
    }

    public WarrantyClaimRecord() {}

    public Long getId() { return id; }
    public String getClaimReason() { return claimReason; }
    public String getClaimantName() { return claimantName; }
    public String getClaimantEmail() { return claimantEmail; }
    public String getStatus() { return status; }
    public LocalDateTime getSubmittedAt() { return submittedAt; }
    public DeviceOwnershipRecord getDevice() { return device; }

    public String getSerialNumber() {
        return device != null ? device.getSerialNumber() : null;
    }

    public void setId(Long id) { this.id = id; }
    public void setClaimReason(String claimReason) { this.claimReason = claimReason; }
    public void setClaimantName(String claimantName) { this.claimantName = claimantName; }
    public void setClaimantEmail(String claimantEmail) { this.claimantEmail = claimantEmail; }
    public void setStatus(String status) { this.status = status; }
    public void setDevice(DeviceOwnershipRecord device) { this.device = device; }
}