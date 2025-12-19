package com.example.demo.service.impl;

import org.springframework.stereotype.Service;

import com.example.demo.model.FraudRule;
import com.example.demo.repository.FraudRuleRepository;
import com.example.demo.service.FraudRuleService;

import java.util.List;
import java.util.NoSuchElementException;
@Service
public class FraudRuleServiceImpl implements FraudRuleService {

    private final FraudRuleRepository repository;

    public FraudRuleServiceImpl(FraudRuleRepository repository) {
        this.repository = repository;
    }

    @Override
    public FraudRule createRule(FraudRule rule) {
        return repository.save(rule);
    }

    @Override
    public FraudRule updateRule(Long id, FraudRule updatedRule) {
        FraudRule rule = repository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Rule not found"));

        rule.setRuleCode(updatedRule.getRuleCode());
        rule.setDescription(updatedRule.getDescription());
        rule.setRuleType(updatedRule.getRuleType());
        rule.setActive(updatedRule.isActive());

        return repository.save(rule);
    }

    @Override
    public List<FraudRule> getActiveRules() {
        return repository.findByActiveTrue();
    }

    @Override
    public FraudRule getRuleByCode(String ruleCode) {
        return repository.findByRuleCode(ruleCode)
                .orElseThrow(() -> new NoSuchElementException("Rule not found"));
    }

    @Override
    public List<FraudRule> getAllRules() {
        return repository.findAll();
    }
}
