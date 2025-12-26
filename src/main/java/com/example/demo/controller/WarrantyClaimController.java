package com.example.demo.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.model.WarrantyClaimRecord;
import com.example.demo.service.WarrantyClaimService;

@RestController
@RequestMapping("/api/claims")
public class WarrantyClaimController {

    private final WarrantyClaimService claimService;

    public WarrantyClaimController(WarrantyClaimService claimService) {
        this.claimService = claimService;
    }

    // ✅ SUBMIT WARRANTY CLAIM
    @PostMapping
    public ResponseEntity<WarrantyClaimRecord> submitClaim(
            @RequestBody WarrantyClaimRecord claim) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(claimService.submitClaim(claim));
    }

    // ✅ UPDATE CLAIM STATUS
    @PutMapping("/{id}/status")
    public ResponseEntity<WarrantyClaimRecord> updateClaimStatus(
            @PathVariable Long id,
            @RequestParam String status) {

        return ResponseEntity.ok(
                claimService.updateClaimStatus(id, status)
        );
    }

    // ✅ GET CLAIM BY ID (NO OPTIONAL LEAK)
    @GetMapping("/{id}")
    public ResponseEntity<WarrantyClaimRecord> getClaimById(
            @PathVariable Long id) {

        return claimService.getClaimById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // ✅ GET ALL CLAIMS
    @GetMapping
    public ResponseEntity<List<WarrantyClaimRecord>> getAllClaims() {
        return ResponseEntity.ok(claimService.getAllClaims());
    }

    // ✅ GET CLAIMS BY SERIAL
    @GetMapping("/serial/{serial}")
    public ResponseEntity<List<WarrantyClaimRecord>> getClaimsBySerial(
            @PathVariable String serial) {

        return ResponseEntity.ok(
                claimService.getClaimsBySerial(serial)
        );
    }
}