package com.example.demo.controller;

import com.example.demo.model.FraudRule;
import com.example.demo.service.FraudRuleService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/fraud-rules")
public class FraudRuleController {

    private final FraudRuleService service;

    public FraudRuleController(FraudRuleService service) {
        this.service = service;
    }

    @PostMapping
    public FraudRule create(@RequestBody FraudRule rule) {
        return service.createRule(rule);
    }

    @GetMapping
    public List<FraudRule> getAll() {
        return service.getAllRules();
    }

    @GetMapping("/{id}")
    public FraudRule getById(@PathVariable Long id) {
        return service.updateRule(id, new FraudRule());
    }

    @GetMapping("/active")
    public List<FraudRule> getActive() {
        return service.getActiveRules();
    }

    @PutMapping("/{id}")
    public FraudRule update(@PathVariable Long id, @RequestBody FraudRule rule) {
        return service.updateRule(id, rule);
    }
}