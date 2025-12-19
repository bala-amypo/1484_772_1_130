package com.example.demo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "warranty_claim_records")
public class WarrantyClaimRecord {

    /* ================= PRIMARY KEY ================= */

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /* ================= FIELDS ================= */

    // Stored redundantly for reference, actual FK handled via device relationship
    @NotBlank
    @Column(nullable = false)
    private String serialNumber;

    @NotBlank
    @Column(nullable = false)
    private String claimantName;

    @Email
    @Column(nullable = true)   // optional
    private String claimantEmail;

    @NotBlank
    @Column(nullable = false)
    private String claimReason;

    @Column(nullable = false)
    private String status;

    @Column(nullable = false, updatable = false)
    private LocalDateTime submittedAt;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    /* ================= RELATIONSHIPS ================= */

    @ManyToOne(optional = false)
    @JoinColumn(name = "device_id", nullable = false)
    private DeviceOwnershipRecord device;

    @OneToMany(mappedBy = "claim", cascade = CascadeType.ALL)
    private List<FraudAlertRecord> fraudAlerts;

    /* ================= CONSTRUCTORS ================= */

    // No-args constructor
    public WarrantyClaimRecord() {
        this.status = "PENDING";
    }

    // Core-fields constructor
    public WarrantyClaimRecord(
            String serialNumber,
            String claimantName,
            String claimantEmail,
            String claimReason,
            DeviceOwnershipRecord device
    ) {
        this.serialNumber = serialNumber;
        this.claimantName = claimantName;
        this.claimantEmail = claimantEmail;
        this.claimReason = claimReason;
        this.device = device;
        this.status = "PENDING";
    }

    /* ================= LIFECYCLE ================= */

    @PrePersist
    protected void onCreate() {
        this.submittedAt = LocalDateTime.now();
        this.createdAt = LocalDateTime.now();
        if (this.status == null) {
            this.status = "PENDING";
        }
    }

    /* ================= STATUS HELPERS ================= */

    public void flag() {
        this.status = "FLAGGED";
    }

    public void approve() {
        this.status = "APPROVED";
    }

    public void reject() {
        this.status = "REJECTED";
    }

    /* ================= GETTERS & SETTERS ================= */

    public Long getId() {
        return id;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getClaimantName() {
        return claimantName;
    }

    public void setClaimantName(String claimantName) {
        this.claimantName = claimantName;
    }

    public String getClaimantEmail() {
        return claimantEmail;
    }

    public void setClaimantEmail(String claimantEmail) {
        this.claimantEmail = claimantEmail;
    }

    public String getClaimReason() {
        return claimReason;
    }

    public void setClaimReason(String claimReason) {
        this.claimReason = claimReason;
    }

    public String getStatus() {
        return status;
    }

    public DeviceOwnershipRecord getDevice() {
        return device;
    }

    public void setDevice(DeviceOwnershipRecord device) {
        this.device = device;
        if (device != null) {
            this.serialNumber = device.getSerialNumber();
        }
    }

    public LocalDateTime getSubmittedAt() {
        return submittedAt;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}
