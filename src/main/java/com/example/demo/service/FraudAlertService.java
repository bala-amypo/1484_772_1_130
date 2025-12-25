package com.example.demo.service;

import com.example.demo.model.FraudAlertRecord;

import java.util.List;

public interface FraudAlertService {
    FraudAlertRecord createAlert(FraudAlertRecord r);
    FraudAlertRecord resolveAlert(Long id);
    List<FraudAlertRecord> getAlertsByClaim(Long claimId);
}
