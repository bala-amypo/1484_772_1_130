package com.example.demo.model;

public class FraudAlertRecord {
    private Long id;
    private String serialNumber;
    private Long claimId;
    private Boolean resolved;

    public FraudAlertRecord(){ }

    public static Builder builder(){ return new Builder(); }
    public static class Builder {
        private final FraudAlertRecord f = new FraudAlertRecord();
        public Builder id(Long id){ f.setId(id); return this; }
        public Builder serialNumber(String s){ f.setSerialNumber(s); return this; }
        public Builder claimId(Long id){ f.setClaimId(id); return this; }
        public Builder resolved(Boolean r){ f.setResolved(r); return this; }
        public FraudAlertRecord build(){ return f; }
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getSerialNumber() { return serialNumber; }
    public void setSerialNumber(String serialNumber) { this.serialNumber = serialNumber; }
    public Long getClaimId() { return claimId; }
    public void setClaimId(Long claimId) { this.claimId = claimId; }
    public Boolean getResolved() { return resolved; }
    public void setResolved(Boolean resolved) { this.resolved = resolved; }
}
