package com.example.demo.model;

public class FraudRule {
    private Long id;
    private String ruleCode;
    private String ruleType;
    private String description;
    private Boolean active;

    public static Builder builder(){ return new Builder(); }
    public static class Builder {
        private final FraudRule r = new FraudRule();
        public Builder id(Long id){ r.setId(id); return this; }
        public Builder ruleCode(String s){ r.setRuleCode(s); return this; }
        public Builder ruleType(String s){ r.setRuleType(s); return this; }
        public Builder description(String s){ r.setDescription(s); return this; }
        public Builder active(Boolean b){ r.setActive(b); return this; }
        public FraudRule build(){ return r; }
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getRuleCode() { return ruleCode; }
    public void setRuleCode(String ruleCode) { this.ruleCode = ruleCode; }
    public String getRuleType() { return ruleType; }
    public void setRuleType(String ruleType) { this.ruleType = ruleType; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public Boolean getActive() { return active; }
    public void setActive(Boolean active) { this.active = active; }
}
