package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "device_ownership_record")
public class DeviceOwnershipRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String serialNumber;

    @Column(nullable = false)
    private String ownerName;

    @Column(nullable = false)
    private String ownerEmail;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate warrantyExpiration;

    private Boolean active = true;

    // ✅ REQUIRED no-args constructor
    public DeviceOwnershipRecord() {}

    /* ===== BUILDER ===== */
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private final DeviceOwnershipRecord d = new DeviceOwnershipRecord();

        public Builder id(Long id) { d.id = id; return this; }
        public Builder serialNumber(String s) { d.serialNumber = s; return this; }
        public Builder ownerName(String o) { d.ownerName = o; return this; }
        public Builder ownerEmail(String e) { d.ownerEmail = e; return this; }
        public Builder warrantyExpiration(LocalDate w) { d.warrantyExpiration = w; return this; }
        public Builder active(Boolean a) { d.active = a; return this; }

        public DeviceOwnershipRecord build() { return d; }
    }

    /* ===== GETTERS & SETTERS (MANDATORY) ===== */

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getSerialNumber() { return serialNumber; }
    public void setSerialNumber(String serialNumber) { this.serialNumber = serialNumber; }

    public String getOwnerName() { return ownerName; }
    public void setOwnerName(String ownerName) { this.ownerName = ownerName; }

    public String getOwnerEmail() { return ownerEmail; }
    public void setOwnerEmail(String ownerEmail) { this.ownerEmail = ownerEmail; }

    public LocalDate getWarrantyExpiration() { return warrantyExpiration; }
    public void setWarrantyExpiration(LocalDate warrantyExpiration) {
        this.warrantyExpiration = warrantyExpiration;
    }

    public Boolean getActive() { return active; }
    public void setActive(Boolean active) { this.active = active; }
}