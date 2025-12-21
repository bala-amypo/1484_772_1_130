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

    @Transient
    private String serialNumber;

    @PrePersist
    public void onCreate() {
        reportDate = LocalDateTime.now();
    }

    public StolenDeviceReport() {}

    public static Builder builder() {
        return new Builder();
    }

    public Long getId() { return id; }
    public String getReportedBy() { return reportedBy; }
    public String getDetails() { return details; }
    public LocalDateTime getReportDate() { return reportDate; }
    public DeviceOwnershipRecord getDevice() { return device; }
    public String getSerialNumber() {
        return serialNumber != null ? serialNumber : device != null ? device.getSerialNumber() : null;
    }

    public void setId(Long id) { this.id = id; }
    public void setReportedBy(String reportedBy) { this.reportedBy = reportedBy; }
    public void setDetails(String details) { this.details = details; }
    public void setDevice(DeviceOwnershipRecord device) { this.device = device; }
    public void setSerialNumber(String serialNumber) { this.serialNumber = serialNumber; }

    public static class Builder {
        private final StolenDeviceReport r = new StolenDeviceReport();
        public Builder id(Long id) { r.setId(id); return this; }
        public Builder reportedBy(String s) { r.setReportedBy(s); return this; }
        public Builder details(String d) { r.setDetails(d); return this; }
        public Builder device(DeviceOwnershipRecord d) { r.setDevice(d); return this; }
        public Builder serialNumber(String s) { r.setSerialNumber(s); return this; }
        public StolenDeviceReport build() { return r; }
    }
}