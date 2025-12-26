// package com.example.demo.model;

// import jakarta.persistence.*;

// @Entity
// public class WarrantyClaimRecord {

//     @Id
//     @GeneratedValue(strategy = GenerationType.IDENTITY)
//     private Long id;

//     private String serialNumber;
//     private String claimReason;
//     private String status = "PENDING";

//     /* ===== BUILDER ===== */
//     public static Builder builder() {
//         return new Builder();
//     }

//     public static class Builder {
//         private final WarrantyClaimRecord c = new WarrantyClaimRecord();

//         public Builder id(Long id) { c.id = id; return this; }
//         public Builder serialNumber(String s) { c.serialNumber = s; return this; }
//         public Builder claimReason(String r) { c.claimReason = r; return this; }
//         public Builder status(String s) { c.status = s; return this; }

//         public WarrantyClaimRecord build() { return c; }
//     }

//     /* ===== GETTERS / SETTERS ===== */
//     public Long getId() { return id; }
//     public void setId(Long id) { this.id = id; }

//     public String getSerialNumber() { return serialNumber; }
//     public String getClaimReason() { return claimReason; }

//     public String getStatus() { return status; }
//     public void setStatus(String status) { this.status = status; }
// }


package com.example.demo.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "warranty_claims")
public class WarrantyClaimRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String claimReason;
    private String status;
    private LocalDate claimDate;

    // CLAIM → DEVICE (Many to One)
    @ManyToOne
    @JoinColumn(name = "device_id", nullable = false)
    private DeviceOwnershipRecord device;

    // -------- Getters and Setters --------

    public Long getId() {
        return id;
    }
 
    public void setId(Long id) {
        this.id = id;
    }
 
    public String getClaimReason() {
        return claimReason;
    }
 
    public void setClaimReason(String claimReason) {
        this.claimReason = claimReason;
    }
 
    public String getStatus() {
        return status;
    }
 
    public void setStatus(String status) {
        this.status = status;
    }
 
    public LocalDate getClaimDate() {
        return claimDate;
    }
 
    public void setClaimDate(LocalDate claimDate) {
        this.claimDate = claimDate;
    }

    public DeviceOwnershipRecord getDevice() {
        return device;
    }

    public void setDevice(DeviceOwnershipRecord device) {
        this.device = device;
    }
}