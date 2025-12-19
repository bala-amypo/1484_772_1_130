package com.example.demo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "stolen_device")
public class StolenDevice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String reportedBy;

    @NotNull
    private LocalDateTime reportDate;

    @NotBlank
    private String details;

    @ManyToOne
    @JoinColumn(name = "device_id", nullable = false)
    private DeviceOwnershipRecord device;

    public StolenDevice() {}

    public Long getId() {
        return id;
    }

    public DeviceOwnershipRecord getDevice() {
        return device;
    }

    public void setDevice(DeviceOwnershipRecord device) {
        this.device = device;
    }

    // ✅ BACKWARD COMPATIBILITY
    public String getSerialNumber() {
        return device != null ? device.getSerialNumber() : null;
    }
}
