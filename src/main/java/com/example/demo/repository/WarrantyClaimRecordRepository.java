package com.example.demo.repository;

import com.example.demo.model.WarrantyClaimRecord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WarrantyClaimRecordRepository
        extends JpaRepository<WarrantyClaimRecord, Long> {

    // Duplicate claim check
    boolean existsBySerialNumberAndClaimReason(String serialNumber, String claimReason);

    // Finds claims by device serial number
    List<WarrantyClaimRecord> findBySerialNumber(String serialNumber);

    // Finds claims by status (PENDING / APPROVED / REJECTED / FLAGGED)
    List<WarrantyClaimRecord> findByStatus(String status);
}
