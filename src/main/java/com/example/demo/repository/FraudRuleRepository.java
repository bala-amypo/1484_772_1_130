package com.example.demo.repository;

import com.example.demo.model.FraudRule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FraudRuleRepository
        extends JpaRepository<FraudRule, Long> {

    // Retrieves specific rule
    Optional<FraudRule> findById(Long id);

    // Retrieves all rules
    List<FraudRule> findAll();

    // Retrieves only active rules
    List<FraudRule> findByActiveTrue();

    // Finds rule by code
    Optional<FraudRule> findByRuleCode(String ruleCode);
}