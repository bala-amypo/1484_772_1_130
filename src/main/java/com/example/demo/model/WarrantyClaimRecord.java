package com.example.demo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.time.LocalDateTime;
import java.util.List;

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
    private List<FraudAlertRecord> alerts;

    @Transient
    private String serialNumber;

    @PrePersist
    public void onCreate() {
        submittedAt = LocalDateTime.now();
    }

    public WarrantyClaimRecord() {}

    public static Builder builder() {
        return new Builder();
    }

    public boolean isEmpty() {
        return id == null;
    }

    public Long getId() { return id; }
    public String getClaimReason() { return claimReason; }
    public String getClaimantName() { return claimantName; }
    public String getClaimantEmail() { return claimantEmail; }
    public String getStatus() { return status; }
    public LocalDateTime getSubmittedAt() { return submittedAt; }
    public DeviceOwnershipRecord getDevice() { return device; }
    public String getSerialNumber() {
        return serialNumber != null ? serialNumber : device != null ? device.getSerialNumber() : null;
    }

    public void setId(Long id) { this.id = id; }
    public void setClaimReason(String claimReason) { this.claimReason = claimReason; }
    public void setClaimantName(String claimantName) { this.claimantName = claimantName; }
    public void setClaimantEmail(String claimantEmail) { this.claimantEmail = claimantEmail; }
    public void setStatus(String status) { this.status = status; }
    public void setDevice(DeviceOwnershipRecord device) { this.device = device; }
    public void setSerialNumber(String serialNumber) { this.serialNumber = serialNumber; }

    public static class Builder {
        private final WarrantyClaimRecord c = new WarrantyClaimRecord();
        public Builder id(Long id) { c.setId(id); return this; }
        public Builder claimReason(String r) { c.setClaimReason(r); return this; }
        public Builder claimantName(String n) { c.setClaimantName(n); return this; }
        public Builder claimantEmail(String e) { c.setClaimantEmail(e); return this; }
        public Builder status(String s) { c.setStatus(s); return this; }
        public Builder device(DeviceOwnershipRecord d) { c.setDevice(d); return this; }
        public Builder serialNumber(String s) { c.setSerialNumber(s); return this; }
        public WarrantyClaimRecord build() { return c; }
    }
}