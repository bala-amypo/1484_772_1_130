package com.example.demo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "stolen_device_reports")
public class StolenDeviceReport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String reportedBy;

    private String details;

    private LocalDateTime reportDate;

    @ManyToOne
    @JoinColumn(name = "device_id", nullable = false)
    private DeviceOwnershipRecord device;

    @PrePersist
    public void onCreate() {
        reportDate = LocalDateTime.now();
    }

    public StolenDeviceReport() {}

    public Long getId() { return id; }
    public String getReportedBy() { return reportedBy; }
    public String getDetails() { return details; }
    public LocalDateTime getReportDate() { return reportDate; }
    public DeviceOwnershipRecord getDevice() { return device; }

    public String getSerialNumber() {
        return device != null ? device.getSerialNumber() : null;
    }

    public void setId(Long id) { this.id = id; }
    public void setReportedBy(String reportedBy) { this.reportedBy = reportedBy; }
    public void setDetails(String details) { this.details = details; }
    public void setDevice(DeviceOwnershipRecord device) { this.device = device; }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private final StolenDeviceReport s = new StolenDeviceReport();

        public Builder id(Long id) { s.id = id; return this; }
        public Builder reportedBy(String reportedBy) { s.reportedBy = reportedBy; return this; }
        public Builder details(String details) { s.details = details; return this; }
        public Builder device(DeviceOwnershipRecord device) { s.device = device; return this; }

        public StolenDeviceReport build() {
            return s;
        }
    }
}