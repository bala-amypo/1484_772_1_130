package com.example.demo.service;

import com.example.demo.model.FraudRule;

import java.util.List;
import java.util.Optional;

public interface FraudRulesService {
    FraudRule createRule(FraudRule r);
    List<FraudRule> getActiveRules();
    Optional<FraudRule> getRuleByCode(String code);
}
