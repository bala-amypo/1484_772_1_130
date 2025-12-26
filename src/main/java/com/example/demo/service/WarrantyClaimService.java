package com.example.demo.service;

import java.util.List;
import java.util.Optional;
import com.example.demo.model.WarrantyClaimRecord;

public interface WarrantyClaimService {
    WarrantyClaimRecord submitClaim(WarrantyClaimRecord claim);
    Optional<WarrantyClaimRecord> getById(Long id);
    List<WarrantyClaimRecord> getAllClaims();
}