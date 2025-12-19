package com.example.demo.service.impl;

import com.example.demo.exception.BadRequestException;
import com.example.demo.model.FraudRule;
import com.example.demo.repository.FraudRuleRepository;
import com.example.demo.service.FraudRuleService;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class FraudRuleServiceImpl implements FraudRuleService {

    private final FraudRuleRepository fraudRuleRepository;

    public FraudRuleServiceImpl(FraudRuleRepository fraudRuleRepository) {
        this.fraudRuleRepository = fraudRuleRepository;
    }

    /* ================= CREATE RULE ================= */

    @Override
    public FraudRule createRule(FraudRule rule) {

        if (fraudRuleRepository.findByRuleCode(rule.getRuleCode()).isPresent()) {
            throw new BadRequestException("Rule already exists");
        }

        return fraudRuleRepository.save(rule);
    }

    /* ================= UPDATE RULE ================= */

    @Override
    public FraudRule updateRule(Long id, FraudRule updatedRule) {

        FraudRule existingRule = fraudRuleRepository.findById(id)
                .orElseThrow(() ->
                        new NoSuchElementException("Request not found")
                );

        existingRule.setRuleCode(updatedRule.getRuleCode());
        existingRule.setRuleType(updatedRule.getRuleType());
        existingRule.setDescription(updatedRule.getDescription());
        existingRule.setActive(updatedRule.isActive());

        return fraudRuleRepository.save(existingRule);
    }

    /* ================= GET ACTIVE RULES ================= */

    @Override
    public List<FraudRule> getActiveRules() {
        return fraudRuleRepository.findByActiveTrue();
    }

    /* ================= GET RULE BY CODE ================= */

    @Override
    public Optional<FraudRule> getRuleByCode(String ruleCode) {
        return fraudRuleRepository.findByRuleCode(ruleCode);
    }

    /* ================= GET ALL RULES ================= */

    @Override
    public List<FraudRule> getAllRules() {
        return fraudRuleRepository.findAll();
    }
}
