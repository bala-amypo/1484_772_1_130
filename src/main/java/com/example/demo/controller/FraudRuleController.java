package com.example.demo.controller;

import com.example.demo.model.FraudRule;
import com.example.demo.service.FraudRuleService;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/fraud-rules")
public class FraudRuleController {

    private final FraudRuleService service;

    public FraudRuleController(FraudRuleService service) {
        this.service = service;
    }

    @PostMapping
    public FraudRule createRule(@Valid @RequestBody FraudRule rule) {
        return service.createRule(rule);
    }

    @PutMapping("/{id}")
    public FraudRule updateRule(
            @PathVariable Long id,
            @RequestBody FraudRule rule) {
        return service.updateRule(id, rule);
    }

    @GetMapping("/active")
    public List<FraudRule> getActiveRules() {
        return service.getActiveRules();
    }

    @GetMapping("/code/{ruleCode}")
    public FraudRule getByCode(@PathVariable String ruleCode) {
        return service.getRuleByCode(ruleCode);
    }

    @GetMapping
    public List<FraudRule> getAllRules() {
        return service.getAllRules();
    }
}
