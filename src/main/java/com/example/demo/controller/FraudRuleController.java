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

    // POST /api/fraud-rules (ADMIN)
    @PostMapping
    public ResponseEntity<?> createRule(@RequestBody FraudRule rule) {
        return ResponseEntity.status(201).body(ruleService.createRule(rule));
    }

    // GET /api/fraud-rules
    @GetMapping
    public ResponseEntity<?> getAllRules() {
        return ResponseEntity.ok(ruleService.getAllRules());
    }

    // GET /api/fraud-rules/{id}
    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        return ResponseEntity.ok(ruleService.getRuleByCode(id.toString()));
    }

    // GET /api/fraud-rules/active
    @GetMapping("/active")
    public ResponseEntity<?> getActiveRules() {
        return ResponseEntity.ok(ruleService.getActiveRules());
    }

    // PUT /api/fraud-rules/{id} (ADMIN)
    @PutMapping("/{id}")
    public ResponseEntity<?> updateRule(
            @PathVariable Long id,
            @RequestBody FraudRule rule
    ) {
        return ResponseEntity.ok(ruleService.updateRule(id, rule));
    }
}