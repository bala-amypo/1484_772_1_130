package com.example.demo.service.impl;

import com.example.demo.model.FraudAlertRecord;
import com.example.demo.repository.FraudAlertRecordRepository;
import com.example.demo.service.FraudAlertService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class FraudAlertServiceImpl implements FraudAlertService {

    private final FraudAlertRecordRepository repository;

    public FraudAlertServiceImpl(FraudAlertRecordRepository repository) {
        this.repository = repository;
    }

    public FraudAlertRecord createAlert(FraudAlertRecord alert) {
        return repository.save(alert);
    }

    public FraudAlertRecord resolveAlert(Long id) {
        FraudAlertRecord alert = repository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Request not found"));
        alert.setResolved(true);
        return repository.save(alert);
    }

    public List<FraudAlertRecord> getAlertsBySerial(String serialNumber) {
        return repository.findByClaim_Device_SerialNumber(serialNumber);
    }

    public List<FraudAlertRecord> getAlertsByClaim(Long claimId) {
        return repository.findByClaim_Id(claimId);
    }

    public List<FraudAlertRecord> getAllAlerts() {
        return repository.findAll();
    }
}