package com.example.demo.controller;

import com.example.demo.model.WarrantyClaimRecord;
import com.example.demo.service.WarrantyClaimService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/claims")
public class WarrantyClaimController {

    private final WarrantyClaimService service;

    public WarrantyClaimController(WarrantyClaimService service) {
        this.service = service;
    }

    @PostMapping
    public WarrantyClaimRecord submit(@RequestBody WarrantyClaimRecord record) {
        return service.submitClaim(record);
    }

    @GetMapping
    public List<WarrantyClaimRecord> getAll() {
        return service.getAllClaims();
    }

    @GetMapping("/{id}")
    public WarrantyClaimRecord getById(@PathVariable Long id) {
        return service.getClaimById(id).orElse(null);
    }

    @GetMapping("/serial/{serial}")
    public List<WarrantyClaimRecord> getBySerial(@PathVariable String serial) {
        return service.getClaimsBySerial(serial);
    }

    @PutMapping("/{id}/status")
    public WarrantyClaimRecord updateStatus(@PathVariable Long id, @RequestParam String status) {
        return service.updateClaimStatus(id, status);
    }
}