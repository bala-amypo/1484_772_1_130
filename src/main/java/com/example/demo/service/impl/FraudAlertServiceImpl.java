package com.example.demo.service.impl;

import com.example.demo.model.FraudAlertRecord;
import com.example.demo.repository.FraudAlertRecordRepository;
import com.example.demo.service.FraudAlertService;

import java.util.List;
import java.util.NoSuchElementException;

public class FraudAlertServiceImpl implements FraudAlertService {
    private final FraudAlertRecordRepository repo;

    public FraudAlertServiceImpl(FraudAlertRecordRepository repo) {
        this.repo = repo;
    }

    @Override
    public FraudAlertRecord createAlert(FraudAlertRecord r) {
        return repo.save(r);
    }

    @Override
    public FraudAlertRecord resolveAlert(Long id) {
        FraudAlertRecord f = repo.findById(id).orElseThrow(NoSuchElementException::new);
        f.setResolved(true);
        return repo.save(f);
    }

    @Override
    public List<FraudAlertRecord> getAlertsByClaim(Long claimId) {
        return repo.findByClaimId(claimId);
    }
}
