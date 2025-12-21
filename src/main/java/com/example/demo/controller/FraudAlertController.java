package com.example.demo.controller;

import com.example.demo.model.FraudAlertRecord;
import com.example.demo.service.FraudAlertService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/fraud-alerts")
public class FraudAlertController {

    private final FraudAlertService alertService;

    public FraudAlertController(FraudAlertService alertService) {
        this.alertService = alertService;
    }

    @PostMapping
    public ResponseEntity<?> createAlert(@RequestBody FraudAlertRecord alert) {
        return ResponseEntity.status(201).body(alertService.createAlert(alert));
    }

    @GetMapping
    public ResponseEntity<?> getAllAlerts() {
        return ResponseEntity.ok(alertService.getAllAlerts());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        return ResponseEntity.ok(alertService.resolveAlert(id));
    }

    @GetMapping("/serial/{serialNumber}")
    public ResponseEntity<?> getBySerial(@PathVariable String serialNumber) {
        return ResponseEntity.ok(alertService.getAlertsBySerial(serialNumber));
    }

    @GetMapping("/claim/{claimId}")
    public ResponseEntity<?> getByClaim(@PathVariable Long claimId) {
        return ResponseEntity.ok(alertService.getAlertsByClaim(claimId));
    }

    @PutMapping("/{id}/resolve")
    public ResponseEntity<?> resolve(@PathVariable Long id) {
        return ResponseEntity.ok(alertService.resolveAlert(id));
    }
}