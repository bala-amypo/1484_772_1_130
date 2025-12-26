// package com.example.demo.model;

// import jakarta.persistence.*;
// import java.util.HashSet;
// import java.util.Set;

// @Entity
// @Table(name = "users")
// public class User {

//     @Id
//     @GeneratedValue(strategy = GenerationType.IDENTITY)
//     private Long id;

//     @Column(unique = true)
//     private String email;

//     private String password;

//     @ElementCollection(fetch = FetchType.EAGER)
//     private Set<String> roles = new HashSet<>();

    
//     public static Builder builder() {
//         return new Builder();
//     }

//     public static class Builder {
//         private final User u = new User();

//         public Builder id(Long id) { u.id = id; return this; }
//         public Builder email(String email) { u.email = email; return this; }
//         public Builder password(String password) { u.password = password; return this; }
//         public Builder roles(Set<String> roles) { u.roles = roles; return this; }

//         public User build() { return u; }
//     }

    
//     public Long getId() { return id; }
//     public void setId(Long id) { this.id = id; }

//     public String getEmail() { return email; }
//     public void setEmail(String email) { this.email = email; }

//     public String getPassword() { return password; }
//     public void setPassword(String password) { this.password = password; }

//     public Set<String> getRoles() { return roles; }
//     public void setRoles(Set<String> roles) { this.roles = roles; }
// }

package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String password;
    private String role;

    // USER → DEVICE (One to Many)
    @JsonIgnore
    @OneToMany(
            mappedBy = "user",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    private List<DeviceOwnershipRecord> devices;

    // -------- Getters and Setters --------

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }
 
    public void setPassword(String password) {
        this.password = password;
    }
 
    public String getRole() {
        return role;
    }
 
    public void setRole(String role) {
        this.role = role;
    }

    public List<DeviceOwnershipRecord> getDevices() {
        return devices;
    }

    public void setDevices(List<DeviceOwnershipRecord> devices) {
        this.devices = devices;
    }
}