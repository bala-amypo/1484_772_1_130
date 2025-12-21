package com.example.demo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "fraud_alert_records")
public class FraudAlertRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String alertType;

    @NotBlank
    private String severity;

    private String message;

    private LocalDateTime alertDate;

    private boolean resolved = false;

    @ManyToOne
    @JoinColumn(name = "claim_id", nullable = false)
    private WarrantyClaimRecord claim;

    @Transient
    private Long claimId;

    @Transient
    private String serialNumber;

    @PrePersist
    public void onCreate() {
        alertDate = LocalDateTime.now();
    }

    public FraudAlertRecord() {}

    public static Builder builder() {
        return new Builder();
    }

    public Long getId() { return id; }
    public boolean getResolved() { return resolved; }
    public WarrantyClaimRecord getClaim() { return claim; }
    public Long getClaimId() {
        return claimId != null ? claimId : claim != null ? claim.getId() : null;
    }
    public String getSerialNumber() {
        return serialNumber != null ? serialNumber :
                claim != null ? claim.getSerialNumber() : null;
    }

    public void setId(Long id) { this.id = id; }
    public void setAlertType(String alertType) { this.alertType = alertType; }
    public void setSeverity(String severity) { this.severity = severity; }
    public void setMessage(String message) { this.message = message; }
    public void setResolved(boolean resolved) { this.resolved = resolved; }
    public void setClaim(WarrantyClaimRecord claim) { this.claim = claim; }
    public void setClaimId(Long claimId) { this.claimId = claimId; }
    public void setSerialNumber(String serialNumber) { this.serialNumber = serialNumber; }

    public static class Builder {
        private final FraudAlertRecord f = new FraudAlertRecord();
        public Builder id(Long id) { f.setId(id); return this; }
        public Builder alertType(String a) { f.setAlertType(a); return this; }
        public Builder severity(String s) { f.setSeverity(s); return this; }
        public Builder message(String m) { f.setMessage(m); return this; }
        public Builder resolved(boolean r) { f.setResolved(r); return this; }
        public Builder claim(WarrantyClaimRecord c) { f.setClaim(c); return this; }
        public Builder claimId(Long id) { f.setClaimId(id); return this; }
        public Builder serialNumber(String s) { f.setSerialNumber(s); return this; }
        public FraudAlertRecord build() { return f; }
    }
}