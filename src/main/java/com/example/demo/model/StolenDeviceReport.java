package com.example.demo.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(
    name = "stolen_device_reports",
    uniqueConstraints = @UniqueConstraint(columnNames = "serialNumber")
)
@Getter
@Setter
@NoArgsConstructor
public class StolenDeviceReport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String serialNumber;

    @Column(nullable = false)
    private String reportedBy;

    private LocalDateTime reportDate;

    private String details;

    // Relationship
    @ManyToOne
    @JoinColumn(name = "device_id")
    private DeviceOwnershipRecord device;

    // Core fields constructor
    public StolenDeviceReport(String serialNumber, String reportedBy) {
        this.serialNumber = serialNumber;
        this.reportedBy = reportedBy;
    }

    @PrePersist
    protected void onCreate() {
        this.reportDate = LocalDateTime.now();
    }
}