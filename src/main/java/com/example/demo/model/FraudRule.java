package com.example.demo.model;

public class FraudRule {
    private Long id;
    private String ruleCode;
    private Boolean active = true;
    private String ruleType;
    private String description;

    public static Builder builder(){ return new Builder(); }
    public static class Builder {
        private final FraudRule r = new FraudRule();
        public Builder id(Long id){ r.id=id; return this; }
        public Builder ruleCode(String c){ r.ruleCode=c; return this; }
        public Builder ruleType(String t){ r.ruleType=t; return this; }
        public Builder description(String d){ r.description=d; return this; }
        public FraudRule build(){ return r; }
    }

    public Long getId(){ return id; }
    public void setId(Long id){ this.id=id; }
    public String getRuleCode(){ return ruleCode; }
}