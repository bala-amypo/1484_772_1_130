package com.example.demo.controller;

import com.example.demo.model.WarrantyClaimRecord;
import com.example.demo.service.WarrantyClaimService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/claims")
public class WarrantyClaimController {

    private final WarrantyClaimService claimService;

    public WarrantyClaimController(WarrantyClaimService claimService) {
        this.claimService = claimService;
    }

    // POST /api/claims
    @PostMapping
    public ResponseEntity<?> submitClaim(@RequestBody WarrantyClaimRecord claim) {
        return ResponseEntity.status(201).body(claimService.submitClaim(claim));
    }

    // GET /api/claims
    @GetMapping
    public ResponseEntity<?> getAllClaims() {
        return ResponseEntity.ok(claimService.getAllClaims());
    }

    // GET /api/claims/{id}
    @GetMapping("/{id}")
    public ResponseEntity<?> getClaimById(@PathVariable Long id) {
        return ResponseEntity.ok(claimService.getClaimById(id));
    }

    // GET /api/claims/serial/{serialNumber}
    @GetMapping("/serial/{serialNumber}")
    public ResponseEntity<?> getBySerial(@PathVariable String serialNumber) {
        return ResponseEntity.ok(claimService.getClaimsBySerial(serialNumber));
    }

    // PUT /api/claims/{id}/status (ADMIN)
    @PutMapping("/{id}/status")
    public ResponseEntity<?> updateStatus(
            @PathVariable Long id,
            @RequestParam String status
    ) {
        return ResponseEntity.ok(claimService.updateClaimStatus(id, status));
    }
}