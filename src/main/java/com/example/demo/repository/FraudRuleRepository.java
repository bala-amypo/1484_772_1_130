package com.example.demo.repository;

import com.example.demo.model.FraudRule;

import java.util.List;
import java.util.Optional;

public interface FraudRuleRepository {
    FraudRule save(FraudRule r);
    Optional<FraudRule> findByRuleCode(String code);
    List<FraudRule> findByActiveTrue();
}
