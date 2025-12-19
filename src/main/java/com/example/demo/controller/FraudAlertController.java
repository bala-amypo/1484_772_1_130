package com.example.demo.controller;

import com.example.demo.model.FraudAlertRecord;
import com.example.demo.service.FraudAlertService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/fraud-alerts")
public class FraudAlertController {

    private final FraudAlertService service;

    public FraudAlertController(FraudAlertService service) {
        this.service = service;
    }

    @PostMapping
    public FraudAlertRecord createAlert(
            @RequestBody FraudAlertRecord alert) {
        return service.createAlert(alert);
    }

    @PutMapping("/{id}/resolve")
    public void resolveAlert(@PathVariable Long id) {
        service.resolveAlert(id);
    }

    @GetMapping("/claim/{claimId}")
    public List<FraudAlertRecord> getByClaim(
            @PathVariable Long claimId) {
        return service.getAlertsByClaim(claimId);
    }

    @GetMapping
    public List<FraudAlertRecord> getAllAlerts() {
        return service.getAllAlerts();
    }
}
