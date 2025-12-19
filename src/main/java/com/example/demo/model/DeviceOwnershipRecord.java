package com.example.demo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(
        name = "device_ownership_records",
        uniqueConstraints = @UniqueConstraint(columnNames = "serialNumber")
)
public class DeviceOwnershipRecord {

    /* ================= PRIMARY KEY ================= */

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /* ================= FIELDS ================= */

    @NotBlank
    @Column(nullable = false, unique = true)
    private String serialNumber;

    @NotBlank
    @Column(nullable = false)
    private String ownerName;

    @Email
    @Column(nullable = true)   // optional
    private String ownerEmail;

    private LocalDate purchaseDate; // optional

    @NotNull
    @Column(nullable = false)
    private LocalDate warrantyExpiration;

    @Column(nullable = false)
    private Boolean active = true;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    /* ================= RELATIONSHIPS ================= */

    @OneToMany(mappedBy = "device", cascade = CascadeType.ALL)
    private List<WarrantyClaimRecord> claims;

    @OneToMany(mappedBy = "device", cascade = CascadeType.ALL)
    private List<StolenDeviceReport> stolenReports;

    /* ================= CONSTRUCTORS ================= */

    // No-args constructor
    public DeviceOwnershipRecord() {
        this.active = true;
    }

    // Core-fields constructor
    public DeviceOwnershipRecord(
            String serialNumber,
            String ownerName,
            String ownerEmail,
            LocalDate purchaseDate,
            LocalDate warrantyExpiration
    ) {
        this.serialNumber = serialNumber;
        this.ownerName = ownerName;
        this.ownerEmail = ownerEmail;
        this.purchaseDate = purchaseDate;
        this.warrantyExpiration = warrantyExpiration;
        this.active = true;
    }

    /* ================= LIFECYCLE ================= */

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
        if (this.active == null) {
            this.active = true;
        }
    }

    /* ================= BUSINESS RULE HELPERS ================= */

    public boolean isEligibleForWarrantyClaim() {
        return Boolean.TRUE.equals(this.active);
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

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getOwnerEmail() {
        return ownerEmail;
    }

    public void setOwnerEmail(String ownerEmail) {
        this.ownerEmail = ownerEmail;
    }

    public LocalDate getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(LocalDate purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public LocalDate getWarrantyExpiration() {
        return warrantyExpiration;
    }

    public void setWarrantyExpiration(LocalDate warrantyExpiration) {
        this.warrantyExpiration = warrantyExpiration;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}
