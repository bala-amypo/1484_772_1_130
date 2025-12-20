package com.example.demo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "device_ownership_records", uniqueConstraints = @UniqueConstraint(columnNames = "serialNumber"))
public class DeviceOwnershipRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false, unique = true)
    private String serialNumber;

    @NotBlank
    private String ownerName;

    @Email
    private String ownerEmail;

    private LocalDate purchaseDate;

    @NotNull
    private LocalDate warrantyExpiration;

    private boolean active = true;

    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "device", cascade = CascadeType.ALL)
    private List<WarrantyClaimRecord> claims;

    @PrePersist
    public void onCreate() {
        createdAt = LocalDateTime.now();
    }

    public DeviceOwnershipRecord() {}

    public Long getId() { return id; }
    public String getSerialNumber() { return serialNumber; }
    public String getOwnerName() { return ownerName; }
    public String getOwnerEmail() { return ownerEmail; }
    public LocalDate getPurchaseDate() { return purchaseDate; }
    public LocalDate getWarrantyExpiration() { return warrantyExpiration; }
    public boolean isActive() { return active; }
    public LocalDateTime getCreatedAt() { return createdAt; }

    public void setId(Long id) { this.id = id; }
    public void setSerialNumber(String serialNumber) { this.serialNumber = serialNumber; }
    public void setOwnerName(String ownerName) { this.ownerName = ownerName; }
    public void setOwnerEmail(String ownerEmail) { this.ownerEmail = ownerEmail; }
    public void setPurchaseDate(LocalDate purchaseDate) { this.purchaseDate = purchaseDate; }
    public void setWarrantyExpiration(LocalDate warrantyExpiration) { this.warrantyExpiration = warrantyExpiration; }
    public void setActive(boolean active) { this.active = active; }
}