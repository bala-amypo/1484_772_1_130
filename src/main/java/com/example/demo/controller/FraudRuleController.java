package com.example.demo.controller;

import com.example.demo.model.FraudRule;
import com.example.demo.service.FraudRuleService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/fraud-rules")
public class FraudRuleController {

    private final FraudRuleService fraudRuleService;

    public FraudRuleController(FraudRuleService fraudRuleService) {
        this.fraudRuleService = fraudRuleService;
    }

    /* ================= CREATE FRAUD RULE ================= */
    /* ADMIN ONLY */

    @PostMapping
    public ResponseEntity<FraudRule> createRule(
            @RequestBody FraudRule rule
    ) {
        FraudRule savedRule = fraudRuleService.createRule(rule);
        return ResponseEntity.ok(savedRule);
    }

    /* ================= GET ALL RULES ================= */
    /* PROTECTED */

    @GetMapping
    public ResponseEntity<List<FraudRule>> getAllRules() {
        return ResponseEntity.ok(
                fraudRuleService.getAllRules()
        );
    }

    /* ================= GET RULE BY ID ================= */
    /* PROTECTED */

    @GetMapping("/{id}")
    public ResponseEntity<FraudRule> getRuleById(
            @PathVariable Long id
    ) {
        Optional<FraudRule> rule = fraudRuleService.getRuleById(id);

        return rule
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /* ================= GET ACTIVE RULES ================= */
    /* PROTECTED */

    @GetMapping("/active")
    public ResponseEntity<List<FraudRule>> getActiveRules() {
        return ResponseEntity.ok(
                fraudRuleService.getActiveRules()
        );
    }

    /* ================= UPDATE RULE ================= */
    /* ADMIN ONLY */

    @PutMapping("/{id}")
    public ResponseEntity<FraudRule> updateRule(
            @PathVariable Long id,
            @RequestBody FraudRule updatedRule
    ) {
        FraudRule rule =
                fraudRuleService.updateRule(id, updatedRule);
        return ResponseEntity.ok(rule);
    }
}
