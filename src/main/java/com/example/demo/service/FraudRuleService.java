package com.example.demo.service;

import com.example.demo.model.FraudRule;
import java.util.List;

public interface FraudRuleService {
    FraudRule createRule(FraudRule rule);
    FraudRule updateRule(Long id, FraudRule rule);
    FraudRule getRuleById(Long id);
    List<FraudRule> getActiveRules();
    List<FraudRule> getAllRules();
}