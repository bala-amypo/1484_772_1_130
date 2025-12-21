package com.example.demo.service.impl;

import com.example.demo.model.FraudRule;
import com.example.demo.repository.FraudRuleRepository;
import com.example.demo.service.FraudRuleService;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class FraudRuleServiceImpl implements FraudRuleService {

    private final FraudRuleRepository repo;

    public FraudRuleServiceImpl(FraudRuleRepository repo) {
        this.repo = repo;
    }

    @Override
    public FraudRule createRule(FraudRule rule) {
        repo.findByRuleCode(rule.getRuleCode())
                .ifPresent(r -> { throw new IllegalArgumentException(); });
        return repo.save(rule);
    }

    @Override
    public FraudRule updateRule(Long id, FraudRule rule) {
        FraudRule existing = repo.findById(id)
                .orElseThrow(NoSuchElementException::new);
        existing.setRuleType(rule.getRuleType());
        existing.setDescription(rule.getDescription());
        existing.setActive(rule.isActive());
        return repo.save(existing);
    }

    @Override
    public List<FraudRule> getActiveRules() {
        return repo.findByActiveTrue();
    }

    @Override
    public Optional<FraudRule> getRuleByCode(String ruleCode) {
        return repo.findByRuleCode(ruleCode);
    }

    @Override
    public List<FraudRule> getAllRules() {
        return repo.findAll();
    }
}