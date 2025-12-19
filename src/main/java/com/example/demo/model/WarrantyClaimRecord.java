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

    @NotBlank
    private String status;

    private LocalDateTime submittedAt;

    /* 🔗 RELATIONSHIP */
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

    // getters & setters
}
