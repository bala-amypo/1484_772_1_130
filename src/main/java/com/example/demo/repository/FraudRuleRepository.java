package com.example.demo.repository;

import com.example.demo.model.FraudRule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FraudRuleRepository
        extends JpaRepository<FraudRule, Long> {

    // Retrieves only active fraud rules
    List<FraudRule> findByActiveTrue();

    // Finds rule by rule code
    Optional<FraudRule> findByRuleCode(String ruleCode);
}
