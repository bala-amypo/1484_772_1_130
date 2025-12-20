package com.example.demo.service.impl;

import com.example.demo.model.FraudRule;
import com.example.demo.repository.FraudRuleRepository;
import com.example.demo.service.FraudRuleService;
import org.springframework.stereotype.Service;

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
        if (repository.findByRuleCode(rule.getRuleCode()).isPresent()) {
            throw new IllegalArgumentException("Rule already exists");
        }
        return repository.save(rule);
    }

    @Override
    public FraudRule updateRule(Long id, FraudRule updatedRule) {
        FraudRule rule = getRuleById(id);
        rule.setRuleType(updatedRule.getRuleType());
        rule.setDescription(updatedRule.getDescription());
        rule.setActive(updatedRule.isActive());
        return repository.save(rule);
    }

    @Override
    public FraudRule getRuleById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Rule not found"));
    }

    @Override
    public List<FraudRule> getActiveRules() {
        return repository.findByActiveTrue();
    }

    @Override
    public List<FraudRule> getAllRules() {
        return repository.findAll();
    }
}