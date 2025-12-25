package com.example.demo.service.impl;

import com.example.demo.model.FraudRule;
import com.example.demo.repository.FraudRuleRepository;
import com.example.demo.service.FraudRulesService;

import java.util.List;
import java.util.Optional;

public class FraudRuleServiceImpl implements FraudRulesService {
    private final FraudRuleRepository repo;

    public FraudRuleServiceImpl(FraudRuleRepository repo) {
        this.repo = repo;
    }

    @Override
    public FraudRule createRule(FraudRule r) {
        Optional<FraudRule> ex = repo.findByRuleCode(r.getRuleCode());
        if (ex.isPresent()) throw new IllegalArgumentException("duplicate rule");
        return repo.save(r);
    }

    @Override
    public List<FraudRule> getActiveRules() {
        return repo.findByActiveTrue();
    }

    @Override
    public Optional<FraudRule> getRuleByCode(String code) {
        return repo.findByRuleCode(code);
    }
}
