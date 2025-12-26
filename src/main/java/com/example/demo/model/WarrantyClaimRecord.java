package com.example.demo.model;

import jakarta.persistence.*;

@Entity
@Table(name = "warranty_claim_record")
public class WarrantyClaimRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String serialNumber;

    @Column(nullable = false)
    private String claimReason;

    private String status = "PENDING";

    public WarrantyClaimRecord() {}

    /* ===== BUILDER (TEST REQUIRED) ===== */
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

    /* ===== GETTERS / SETTERS (MANDATORY) ===== */
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
}