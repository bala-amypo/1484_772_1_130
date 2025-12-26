// package com.example.demo.model;

// import jakarta.persistence.*;
// import java.time.LocalDate;

// @Entity
// public class DeviceOwnershipRecord {

//     @Id
//     @GeneratedValue(strategy = GenerationType.IDENTITY)
//     private Long id;

//     @Column(unique = true)
//     private String serialNumber;

//     private String ownerName;
//     private String ownerEmail;
//     private LocalDate warrantyExpiration;
//     private Boolean active = true;

//     /* ===== BUILDER ===== */
//     public static Builder builder() {
//         return new Builder();
//     }

//     public static class Builder {
//         private final DeviceOwnershipRecord d = new DeviceOwnershipRecord();

//         public Builder id(Long id) { d.id = id; return this; }
//         public Builder serialNumber(String s) { d.serialNumber = s; return this; }
//         public Builder ownerName(String o) { d.ownerName = o; return this; }
//         public Builder ownerEmail(String e) { d.ownerEmail = e; return this; }
//         public Builder warrantyExpiration(LocalDate w) { d.warrantyExpiration = w; return this; }
//         public Builder active(Boolean a) { d.active = a; return this; }

//         public DeviceOwnershipRecord build() { return d; }
//     }

//     /* ===== GETTERS / SETTERS ===== */
//     public Long getId() { return id; }
//     public void setId(Long id) { this.id = id; }

//     public String getSerialNumber() { return serialNumber; }
//     public void setSerialNumber(String serialNumber) { this.serialNumber = serialNumber; }

//     public String getOwnerName() { return ownerName; }
//     public String getOwnerEmail() { return ownerEmail; }

//     public LocalDate getWarrantyExpiration() { return warrantyExpiration; }

//     public Boolean getActive() { return active; }
//     public void setActive(Boolean active) { this.active = active; }
// }


package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "devices")
public class DeviceOwnershipRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String serialNumber;
    private String deviceType;
    private boolean active;
    private LocalDate purchaseDate;

    // DEVICE → USER (Many to One)
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    // DEVICE → CLAIM (One to Many)
    @JsonIgnore
    @OneToMany(
            mappedBy = "device",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    private List<WarrantyClaimRecord> claims;

    // -------- Getters and Setters --------

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSerialNumber() {
        return serialNumber;
    }
 
    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }
 
    public String getDeviceType() {
        return deviceType;
    }
 
    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }
 
    public boolean isActive() {
        return active;
    }
 
    public void setActive(boolean active) {
        this.active = active;
    }
 
    public LocalDate getPurchaseDate() {
        return purchaseDate;
    }
 
    public void setPurchaseDate(LocalDate purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public User getUser() {
        return user;
    }
 
    public void setUser(User user) {
        this.user = user;
    }

    public List<WarrantyClaimRecord> getClaims() {
        return claims;
    }

    public void setClaims(List<WarrantyClaimRecord> claims) {
        this.claims = claims;
    }
}