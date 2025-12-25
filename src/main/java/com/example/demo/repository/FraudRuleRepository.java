package com.example.demo.repository;

import com.example.demo.model.FraudRule;

import java.util.List;
import java.util.Optional;

public interface FraudRuleRepository {
    Optional<FraudRule> findByRuleCode(String c);
    List<FraudRule> findByActiveTrue();
    FraudRule save(FraudRule r);
}
