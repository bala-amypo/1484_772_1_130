package com.example.demo.controller;

import com.example.demo.model.WarrantyClaimRecord;
import com.example.demo.service.WarrantyClaimService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/claims")
public class WarrantyClaimController {

    private final WarrantyClaimService warrantyClaimService;

    public WarrantyClaimController(WarrantyClaimService warrantyClaimService) {
        this.warrantyClaimService = warrantyClaimService;
    }

    /* ================= SUBMIT CLAIM ================= */
    /* PROTECTED */

    @PostMapping
    public ResponseEntity<WarrantyClaimRecord> submitClaim(
            @RequestBody WarrantyClaimRecord claim
    ) {
        WarrantyClaimRecord savedClaim =
                warrantyClaimService.submitClaim(claim);
        return ResponseEntity.ok(savedClaim);
    }

    /* ================= GET ALL CLAIMS ================= */
    /* PROTECTED */

    @GetMapping
    public ResponseEntity<List<WarrantyClaimRecord>> getAllClaims() {
        return ResponseEntity.ok(
                warrantyClaimService.getAllClaims()
        );
    }

    /* ================= GET CLAIM BY ID ================= */
    /* PROTECTED */

    @GetMapping("/{id}")
    public ResponseEntity<WarrantyClaimRecord> getClaimById(
            @PathVariable Long id
    ) {
        Optional<WarrantyClaimRecord> claim =
                warrantyClaimService.getClaimById(id);

        return claim
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /* ================= GET CLAIMS BY SERIAL ================= */
    /* PROTECTED */

    @GetMapping("/serial/{serialNumber}")
    public ResponseEntity<List<WarrantyClaimRecord>> getClaimsBySerial(
            @PathVariable String serialNumber
    ) {
        return ResponseEntity.ok(
                warrantyClaimService.getClaimsBySerial(serialNumber)
        );
    }

    /* ================= UPDATE CLAIM STATUS ================= */
    /* ADMIN ONLY */

    @PutMapping("/{id}/status")
    public ResponseEntity<WarrantyClaimRecord> updateClaimStatus(
            @PathVariable Long id,
            @RequestParam String status
    ) {
        WarrantyClaimRecord updatedClaim =
                warrantyClaimService.updateClaimStatus(id, status);
        return ResponseEntity.ok(updatedClaim);
    }
}
