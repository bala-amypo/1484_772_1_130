package com.example.demo.controller;

import com.example.demo.model.FraudRule;
import com.example.demo.service.FraudRuleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/fraud-rules")
public class FraudRuleController {

    private final FraudRuleService ruleService;

    public FraudRuleController(FraudRuleService ruleService) {
        this.ruleService = ruleService;
    }

    @PostMapping
    public ResponseEntity<?> createRule(@RequestBody FraudRule rule) {
        return ResponseEntity.status(201).body(ruleService.createRule(rule));
    }

    @GetMapping
    public ResponseEntity<?> getAllRules() {
        return ResponseEntity.ok(ruleService.getAllRules());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        return ResponseEntity.ok(ruleService.getRuleByCode(id.toString()));
    }

    @GetMapping("/active")
    public ResponseEntity<?> getActiveRules() {
        return ResponseEntity.ok(ruleService.getActiveRules());
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateRule(
            @PathVariable Long id,
            @RequestBody FraudRule rule
    ) {
        return ResponseEntity.ok(ruleService.updateRule(id, rule));
    }
}