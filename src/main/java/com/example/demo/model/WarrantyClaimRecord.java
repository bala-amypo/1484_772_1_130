package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "warranty_claim_records")
@Getter
@Setter
@NoArgsConstructor
public class WarrantyClaimRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String serialNumber;

    @Column(nullable = false)
    private String claimantName;

    private String claimantEmail;

    @Column(nullable = false)
    private String claimReason;

    private LocalDateTime submittedAt;

    @Column(nullable = false)
    private String status = "PENDING";

    private LocalDateTime createdAt;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "device_id")
    private DeviceOwnershipRecord device;

    @JsonIgnore
    @OneToMany(mappedBy = "claim", cascade = CascadeType.ALL)
    private Set<FraudAlertRecord> alerts = new HashSet<>();

    public WarrantyClaimRecord(
            String serialNumber,
            String claimantName,
            String claimReason
    ) {
        this.serialNumber = serialNumber;
        this.claimantName = claimantName;
        this.claimReason = claimReason;
        this.status = "PENDING";
    }

    @PrePersist
    protected void onCreate() {
        this.submittedAt = LocalDateTime.now();
        this.createdAt = LocalDateTime.now();
        if (status == null) {
            status = "PENDING";
        }
    }
}