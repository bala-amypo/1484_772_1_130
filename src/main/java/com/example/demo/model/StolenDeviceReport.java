package com.example.demo.model;

public class StolenDeviceReport {
    private Long id;
    private String serialNumber;

    public static Builder builder(){ return new Builder(); }
    public static class Builder {
        private final StolenDeviceReport s = new StolenDeviceReport();
        public Builder id(Long id){ s.setId(id); return this; }
        public Builder serialNumber(String ser){ s.setSerialNumber(ser); return this; }
        public StolenDeviceReport build(){ return s; }
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getSerialNumber() { return serialNumber; }
    public void setSerialNumber(String serialNumber) { this.serialNumber = serialNumber; }
}
