package com.example.demo.repository;

import com.example.demo.model.WarrantyClaimRecord;

import java.util.List;
import java.util.Optional;

public interface WarrantyClaimRecordRepository {
    boolean existsBySerialNumberAndClaimReason(String s, String r);
    Optional<WarrantyClaimRecord> findById(Long id);
    List<WarrantyClaimRecord> findBySerialNumber(String s);
    List<WarrantyClaimRecord> findAll();
    WarrantyClaimRecord save(WarrantyClaimRecord c);
}
