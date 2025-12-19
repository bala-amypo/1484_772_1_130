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

    /* 🔗 RELATIONSHIP */
    @ManyToOne
    @JoinColumn(name = "device_id", nullable = false)
    private DeviceOwnershipRecord device;

    public StolenDevice() {}

    // getters & setters
}
