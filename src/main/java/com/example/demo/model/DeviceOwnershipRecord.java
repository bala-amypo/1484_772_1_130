package com.example.demo.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(
        name = "device_ownership_records",
        uniqueConstraints = @UniqueConstraint(columnNames = "serialNumber")
)
@Getter
@Setter
@NoArgsConstructor
public class DeviceOwnershipRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String serialNumber;

    @Column(nullable = false)
    private String ownerName;

    private String ownerEmail;

    private LocalDate purchaseDate;

    @Column(nullable = false)
    private LocalDate warrantyExpiration;

    private Boolean active = true;

    private LocalDateTime createdAt;

   
    @OneToMany(mappedBy = "device", cascade = CascadeType.ALL)
    private Set<WarrantyClaimRecord> claims = new HashSet<>();

    
    @OneToMany(mappedBy = "device", cascade = CascadeType.ALL)
    private Set<StolenDeviceReport> stolenReports = new HashSet<>();

    public DeviceOwnershipRecord(String serialNumber, String ownerName, LocalDate warrantyExpiration) {
        this.serialNumber = serialNumber;
        this.ownerName = ownerName;
        this.warrantyExpiration = warrantyExpiration;
        this.active = true;
    }

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
        if (active == null) {
            active = true;
        }
    }
}