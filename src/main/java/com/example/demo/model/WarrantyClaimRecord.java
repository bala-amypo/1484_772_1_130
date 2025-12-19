package com.example.demo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "warranty_claim_record")
public class WarrantyClaimRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String claimReason;

    @NotBlank
    private String claimantName;

    @Email
    @NotBlank
    private String claimantEmail;

    private String status;

    private LocalDateTime submittedAt;

    @ManyToOne
    @JoinColumn(name = "device_id", nullable = false)
    private DeviceOwnershipRecord device;

    @PrePersist
    public void onCreate() {
        this.submittedAt = LocalDateTime.now();
        if (this.status == null) {
            this.status = "PENDING";
        }
    }

    public WarrantyClaimRecord() {}

    public Long getId() {
        return id;
    }

    // ✅ needed for FraudAlert backward compatibility
    public void setId(Long id) {
        this.id = id;
    }

    public String getClaimReason() {
        return claimReason;
    }

    public void setClaimReason(String claimReason) {
        this.claimReason = claimReason;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public DeviceOwnershipRecord getDevice() {
        return device;
    }

    public void setDevice(DeviceOwnershipRecord device) {
        this.device = device;
    }

    // ✅ BACKWARD COMPATIBILITY (services expect this)
    public String getSerialNumber() {
        return device != null ? device.getSerialNumber() : null;
    }
}
