package com.example.demo.model;

import java.time.LocalDate;

public class DeviceOwnershipRecord {
    private Long id;
    private String serialNumber;
    private String ownerName;
    private String ownerEmail;
    private LocalDate warrantyExpiration;
    private Boolean active = true;

    public static Builder builder(){ return new Builder(); }

    public static class Builder {
        private final DeviceOwnershipRecord d = new DeviceOwnershipRecord();
        public Builder id(Long id){ d.id=id; return this; }
        public Builder serialNumber(String s){ d.serialNumber=s; return this; }
        public Builder ownerName(String o){ d.ownerName=o; return this; }
        public Builder ownerEmail(String e){ d.ownerEmail=e; return this; }
        public Builder warrantyExpiration(LocalDate w){ d.warrantyExpiration=w; return this; }
        public Builder active(Boolean a){ d.active=a; return this; }
        public DeviceOwnershipRecord build(){ return d; }
    }

    public Long getId(){ return id; }
    public void setId(Long id){ this.id=id; }

    public String getSerialNumber(){ return serialNumber; }
    public void setSerialNumber(String s){ this.serialNumber=s; }

    public Boolean getActive(){ return active; }
    public void setActive(Boolean a){ this.active=a; }

    public LocalDate getWarrantyExpiration(){ return warrantyExpiration; }
}