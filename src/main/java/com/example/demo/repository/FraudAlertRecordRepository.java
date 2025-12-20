package com.example.demo.repository;

import com.example.demo.model.FraudAlertRecord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FraudAlertRecordRepository
        extends JpaRepository<FraudAlertRecord, Long> {

    List<FraudAlertRecord> findByClaim_Id(Long claimId);

    List<FraudAlertRecord> findByClaim_Device_SerialNumber(String serialNumber);

    List<FraudAlertRecord> findByResolved(boolean resolved);
}