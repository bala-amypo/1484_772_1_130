package com.example.demo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;

@Entity
@Table(
        name = "stolen_device_reports",
        uniqueConstraints = @UniqueConstraint(columnNames = "serialNumber")
)
public class StolenDeviceReport {

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
    private String reportedBy;

    @Column(nullable = false, updatable = false)
    private LocalDateTime reportDate;

    @Column(nullable = true)
    private String details;

    /* ================= RELATIONSHIPS ================= */

    @ManyToOne(optional = false)
    @JoinColumn(name = "device_id", nullable = false)
    private DeviceOwnershipRecord device;

    /* ================= CONSTRUCTORS ================= */

    // No-args constructor
    public StolenDeviceReport() {
    }

    // Core-fields constructor
    public StolenDeviceReport(
            String serialNumber,
            String reportedBy,
            String details,
            DeviceOwnershipRecord device
    ) {
        this.serialNumber = serialNumber;
        this.reportedBy = reportedBy;
        this.details = details;
        this.device = device;
    }

    /* ================= LIFECYCLE ================= */

    @PrePersist
    protected void onCreate() {
        this.reportDate = LocalDateTime.now();
    }

    /* ================= GETTERS & SETTERS ================= */

    public Long getId() {
        return id;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public String getReportedBy() {
        return reportedBy;
    }

    public String getDetails() {
        return details;
    }

    public LocalDateTime getReportDate() {
        return reportDate;
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
}
