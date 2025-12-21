package com.example.demo.repository;

import com.example.demo.model.WarrantyClaimRecord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface WarrantyClaimRecordRepository
        extends JpaRepository<WarrantyClaimRecord, Long> {

    // Retrieves specific claim
    Optional<WarrantyClaimRecord> findById(Long id);

    // Retrieves all claims
    List<WarrantyClaimRecord> findAll();

    // Finds claims by device serial
    List<WarrantyClaimRecord> findBySerialNumber(String serialNumber);

    // Duplicate claim check
    boolean existsBySerialNumberAndClaimReason(
            String serialNumber,
            String claimReason
    );

    // Finds claims by status
    List<WarrantyClaimRecord> findByStatus(String status);
}