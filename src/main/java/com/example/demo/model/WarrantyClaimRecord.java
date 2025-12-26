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

@Entity
public class WarrantyClaimRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String serialNumber;

    private String claimReason;

    private String status = "PENDING";

    @ManyToOne
    @JoinColumn(name = "device_id", nullable = false)
    private DeviceOwnershipRecord device;

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private final WarrantyClaimRecord c = new WarrantyClaimRecord();

        public Builder id(Long id) { c.id = id; return this; }
        public Builder serialNumber(String s) { c.serialNumber = s; return this; }
        public Builder claimReason(String r) { c.claimReason = r; return this; }
        public Builder status(String s) { c.status = s; return this; }

        public WarrantyClaimRecord build() { return c; }
    }


    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getSerialNumber() { return serialNumber; }
    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getClaimReason() { return claimReason; }
    public void setClaimReason(String claimReason) {
        this.claimReason = claimReason;
    }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public DeviceOwnershipRecord getDevice() {
        return device;
    }

    public void setDevice(DeviceOwnershipRecord device) {
        this.device = device;
    }
}