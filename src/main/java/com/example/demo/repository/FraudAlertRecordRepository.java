package com.example.demo.repository;

import com.example.demo.model.FraudAlertRecord;

import java.util.List;
import java.util.Optional;

public interface FraudAlertRecordRepository {
    FraudAlertRecord save(FraudAlertRecord r);
    Optional<FraudAlertRecord> findById(Long id);
    List<FraudAlertRecord> findByClaimId(Long claimId);
}
