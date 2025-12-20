package com.example.demo.repository;

import com.example.demo.model.WarrantyClaimRecord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WarrantyClaimRecordRepository
        extends JpaRepository<WarrantyClaimRecord, Long> {

    List<WarrantyClaimRecord> findByDevice_SerialNumber(String serialNumber);

    boolean existsByDevice_SerialNumberAndClaimReason(
            String serialNumber,
            String claimReason
    );
}