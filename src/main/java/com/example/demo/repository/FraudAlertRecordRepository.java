package com.example.demo.repository;

import com.example.demo.model.FraudAlertRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FraudAlertRecordRepository
        extends JpaRepository<FraudAlertRecord, Long> {

    @Query("SELECT f FROM FraudAlertRecord f WHERE f.claim.id = :claimId")
    List<FraudAlertRecord> findByClaimId(@Param("claimId") Long claimId);

    List<FraudAlertRecord> findBySerialNumber(String serialNumber);
}