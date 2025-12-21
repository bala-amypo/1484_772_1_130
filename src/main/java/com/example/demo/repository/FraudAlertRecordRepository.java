package com.example.demo.repository;

import com.example.demo.model.FraudAlertRecord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FraudAlertRecordRepository
        extends JpaRepository<FraudAlertRecord, Long> {

    // Retrieves specific alert
    Optional<FraudAlertRecord> findById(Long id);

    // Retrieves all alerts
    List<FraudAlertRecord> findAll();

    // Finds alerts by claim
    List<FraudAlertRecord> findByClaimId(Long claimId);

    // Finds alerts by device serial
    List<FraudAlertRecord> findBySerialNumber(String serialNumber);

    // Finds alerts by resolution status
    List<FraudAlertRecord> findByResolved(Boolean resolved);
}