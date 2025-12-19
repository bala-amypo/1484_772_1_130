package com.example.demo.controller;

import com.example.demo.model.WarrantyClaimRecord;
import com.example.demo.service.WarrantyClaimService;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/claims")
public class WarrantyClaimController {

    private final WarrantyClaimService service;

    public WarrantyClaimController(WarrantyClaimService service) {
        this.service = service;
    }

    @PostMapping
    public WarrantyClaimRecord submitClaim(
            @Valid @RequestBody WarrantyClaimRecord claim) {
        return service.submitClaim(claim);
    }

    @PutMapping("/{id}/status")
    public void updateStatus(
            @PathVariable Long id,
            @RequestParam String status) {
        service.updateClaimStatus(id, status);
    }

    @GetMapping("/{id}")
    public WarrantyClaimRecord getById(@PathVariable Long id) {
        return service.getClaimById(id);
    }

    @GetMapping("/serial/{serialNumber}")
    public List<WarrantyClaimRecord> getBySerial(
            @PathVariable String serialNumber) {
        return service.getClaimsBySerial(serialNumber);
    }

    @GetMapping
    public List<WarrantyClaimRecord> getAllClaims() {
        return service.getAllClaims();
    }
}
