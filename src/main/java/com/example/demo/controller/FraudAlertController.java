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

    // POST /api/fraud-alerts (ADMIN)
    @PostMapping
    public ResponseEntity<?> createAlert(@RequestBody FraudAlertRecord alert) {
        return ResponseEntity.status(201).body(alertService.createAlert(alert));
    }

    // GET /api/fraud-alerts
    @GetMapping
    public ResponseEntity<?> getAllAlerts() {
        return ResponseEntity.ok(alertService.getAllAlerts());
    }

    // GET /api/fraud-alerts/{id}
    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        return ResponseEntity.ok(alertService.resolveAlert(id));
    }

    // GET /api/fraud-alerts/serial/{serialNumber}
    @GetMapping("/serial/{serialNumber}")
    public ResponseEntity<?> getBySerial(@PathVariable String serialNumber) {
        return ResponseEntity.ok(alertService.getAlertsBySerial(serialNumber));
    }

    // GET /api/fraud-alerts/claim/{claimId}
    @GetMapping("/claim/{claimId}")
    public ResponseEntity<?> getByClaim(@PathVariable Long claimId) {
        return ResponseEntity.ok(alertService.getAlertsByClaim(claimId));
    }

    // PUT /api/fraud-alerts/{id}/resolve (ADMIN)
    @PutMapping("/{id}/resolve")
    public ResponseEntity<?> resolve(@PathVariable Long id) {
        return ResponseEntity.ok(alertService.resolveAlert(id));
    }
}