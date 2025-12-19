package com.example.demo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(
    name = "device_ownership_record",
    uniqueConstraints = @UniqueConstraint(columnNames = "serialNumber")
)
public class DeviceOwnershipRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Serial number is required")
    @Column(nullable = false, unique = true)
    private String serialNumber;

    @NotBlank(message = "Owner name is required")
    private String ownerName;

    @Email(message = "Invalid email format")
    @NotBlank(message = "Owner email is required")
    private String ownerEmail;

    @PastOrPresent
    private LocalDate purchaseDate;

    @Future
    private LocalDate warrantyExpiration;

    private boolean active = true;

    /* 🔗 RELATIONSHIP */
    @OneToMany(mappedBy = "device", cascade = CascadeType.ALL)
    private List<WarrantyClaimRecord> claims;

    public DeviceOwnershipRecord() {}

    // getters & setters
}
