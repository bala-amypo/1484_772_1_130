package com.example.demo.service.impl;

import com.example.demo.model.FraudAlertRecord;
import com.example.demo.repository.FraudAlertRecordRepository;
import com.example.demo.service.FraudAlertService;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class FraudAlertServiceImpl implements FraudAlertService {

    private final FraudAlertRecordRepository fraudAlertRepository;

    public FraudAlertServiceImpl(FraudAlertRecordRepository fraudAlertRepository) {
        this.fraudAlertRepository = fraudAlertRepository;
    }

    /* ================= CREATE ALERT ================= */

    @Override
    public FraudAlertRecord createAlert(FraudAlertRecord alert) {
        return fraudAlertRepository.save(alert);
    }

    /* ================= RESOLVE ALERT ================= */

    @Override
    public FraudAlertRecord resolveAlert(Long id) {

        FraudAlertRecord alert = fraudAlertRepository.findById(id)
                .orElseThrow(() ->
                        new NoSuchElementException("Request not found")
                );

        alert.setResolved(true);
        return fraudAlertRepository.save(alert);
    }

    /* ================= GET ALERTS BY SERIAL ================= */

    @Override
    public List<FraudAlertRecord> getAlertsBySerial(String serialNumber) {
        return fraudAlertRepository.findBySerialNumber(serialNumber);
    }

    /* ================= GET ALERTS BY CLAIM ================= */

    @Override
    public List<FraudAlertRecord> getAlertsByClaim(Long claimId) {
        return fraudAlertRepository.findByClaimId(claimId);
    }

    /* ================= GET ALL ALERTS ================= */

    @Override
    public List<FraudAlertRecord> getAllAlerts() {
        return fraudAlertRepository.findAll();
    }
}
