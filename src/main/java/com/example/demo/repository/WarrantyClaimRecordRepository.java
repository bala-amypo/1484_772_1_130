package com.example.demo.repository;

import com.example.demo.model.WarrantyClaimRecord;

import java.util.List;
import java.util.Optional;

public interface WarrantyClaimRecordRepository {
    WarrantyClaimRecord save(WarrantyClaimRecord r);
    Optional<WarrantyClaimRecord> findById(Long id);
    List<WarrantyClaimRecord> findAll();
    boolean existsBySerialNumberAndClaimReason(String serial, String reason);
    List<WarrantyClaimRecord> findBySerialNumber(String serial);
}
