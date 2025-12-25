package com.example.demo.model;

import java.util.HashSet;
import java.util.Set;

public class User {
    private Long id;
    private String email;
    private String password;
    private String name;
    private Set<String> roles = new HashSet<>();

    public static Builder builder(){ return new Builder(); }
    public static class Builder {
        private final User u = new User();
        public Builder id(Long id){ u.setId(id); return this; }
        public Builder email(String s){ u.setEmail(s); return this; }
        public Builder password(String s){ u.setPassword(s); return this; }
        public Builder name(String s){ u.setName(s); return this; }
        public Builder roles(Set<String> r){ u.setRoles(r); return this; }
        public User build(){ return u; }
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public Set<String> getRoles() { return roles; }
    public void setRoles(Set<String> roles) { this.roles = roles == null ? new HashSet<>() : roles; }
}
