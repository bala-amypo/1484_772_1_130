package com.example.demo.controller;

import com.example.demo.model.FraudAlertRecord;
import com.example.demo.service.FraudAlertService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/fraud-alerts")
public class FraudAlertController {

    private final FraudAlertService fraudAlertService;

    public FraudAlertController(FraudAlertService fraudAlertService) {
        this.fraudAlertService = fraudAlertService;
    }

    /* ================= CREATE FRAUD ALERT ================= */
    /* ADMIN ONLY */

    @PostMapping
    public ResponseEntity<FraudAlertRecord> createAlert(
            @RequestBody FraudAlertRecord alert
    ) {
        FraudAlertRecord savedAlert =
                fraudAlertService.createAlert(alert);
        return ResponseEntity.ok(savedAlert);
    }

    /* ================= GET ALL ALERTS ================= */
    /* PROTECTED */

    @GetMapping
    public ResponseEntity<List<FraudAlertRecord>> getAllAlerts() {
        return ResponseEntity.ok(
                fraudAlertService.getAllAlerts()
        );
    }

    /* ================= GET ALERT BY ID ================= */
    /* PROTECTED */

    @GetMapping("/{id}")
    public ResponseEntity<FraudAlertRecord> getAlertById(
            @PathVariable Long id
    ) {
        Optional<FraudAlertRecord> alert =
                fraudAlertService.getAlertById(id);

        return alert
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /* ================= GET ALERTS BY SERIAL ================= */
    /* PROTECTED */

    @GetMapping("/serial/{serialNumber}")
    public ResponseEntity<List<FraudAlertRecord>> getAlertsBySerial(
            @PathVariable String serialNumber
    ) {
        return ResponseEntity.ok(
                fraudAlertService.getAlertsBySerial(serialNumber)
        );
    }

    /* ================= GET ALERTS BY CLAIM ================= */
    /* PROTECTED */

    @GetMapping("/claim/{claimId}")
    public ResponseEntity<List<FraudAlertRecord>> getAlertsByClaim(
            @PathVariable Long claimId
    ) {
        return ResponseEntity.ok(
                fraudAlertService.getAlertsByClaim(claimId)
        );
    }

    /* ================= RESOLVE ALERT ================= */
    /* ADMIN ONLY */

    @PutMapping("/{id}/resolve")
    public ResponseEntity<FraudAlertRecord> resolveAlert(
            @PathVariable Long id
    ) {
        FraudAlertRecord resolvedAlert =
                fraudAlertService.resolveAlert(id);
        return ResponseEntity.ok(resolvedAlert);
    }
}
