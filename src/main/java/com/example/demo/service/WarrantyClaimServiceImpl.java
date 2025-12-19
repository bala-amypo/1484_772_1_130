package com.example.demo.service.impl;

import com.example.demo.model.DeviceOwnershipRecord;
import com.example.demo.model.FraudAlertRecord;
import com.example.demo.model.WarrantyClaimRecord;
import com.example.demo.repository.*;

import com.example.demo.service.WarrantyClaimService;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;
@Service
public class WarrantyClaimServiceImpl implements WarrantyClaimService {

    private final WarrantyClaimRecordRepository claimRepo;
    private final DeviceOwnershipRecordRepository deviceRepo;
    private final StolenDeviceReportRepository stolenRepo;
    private final FraudAlertRecordRepository alertRepo;
    private final FraudRuleRepository ruleRepo;

    // EXACT ORDER REQUIRED
    public WarrantyClaimServiceImpl(
            WarrantyClaimRecordRepository claimRepo,
            DeviceOwnershipRecordRepository deviceRepo,
            StolenDeviceReportRepository stolenRepo,
            FraudAlertRecordRepository alertRepo,
            FraudRuleRepository ruleRepo) {

        this.claimRepo = claimRepo;
        this.deviceRepo = deviceRepo;
        this.stolenRepo = stolenRepo;
        this.alertRepo = alertRepo;
        this.ruleRepo = ruleRepo;
    }

    @Override
    public WarrantyClaimRecord submitClaim(WarrantyClaimRecord claim) {

        DeviceOwnershipRecord device = deviceRepo
                .findBySerialNumber(claim.getSerialNumber())
                .orElseThrow(() -> new NoSuchElementException("Device not found"));

        if (claimRepo.existsBySerialNumberAndClaimReason(
                claim.getSerialNumber(), claim.getClaimReason())) {
            throw new IllegalArgumentException("Duplicate claim");
        }

        boolean flagged = false;

        if (!device.isActive()) {
            flagged = true;
            createAlert(claim, "INACTIVE_DEVICE", "HIGH", "Device inactive");
        }

        if (stolenRepo.existsBySerialNumber(claim.getSerialNumber())) {
            flagged = true;
            createAlert(claim, "STOLEN_DEVICE", "CRITICAL", "Device reported stolen");
        }

        if (device.getWarrantyExpiration() != null &&
                device.getWarrantyExpiration().isBefore(LocalDate.now())) {
            flagged = true;
            createAlert(claim, "WARRANTY_EXPIRED", "MEDIUM", "Warranty expired");
        }

        claim.setStatus(flagged ? "FLAGGED" : "PENDING");

        return claimRepo.save(claim);
    }

    private void createAlert(WarrantyClaimRecord claim,
                             String type,
                             String severity,
                             String message) {

        FraudAlertRecord alert = new FraudAlertRecord();
        alert.setClaimId(claim.getId());
        alert.setSerialNumber(claim.getSerialNumber());
        alert.setAlertType(type);
        alert.setSeverity(severity);
        alert.setMessage(message);

        alertRepo.save(alert);
    }

    @Override
    public void updateClaimStatus(Long claimId, String status) {
        WarrantyClaimRecord claim = claimRepo.findById(claimId)
                .orElseThrow(() -> new NoSuchElementException("Claim not found"));
        claim.setStatus(status);
        claimRepo.save(claim);
    }

    @Override
    public WarrantyClaimRecord getClaimById(Long id) {
        return claimRepo.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Claim not found"));
    }

    @Override
    public List<WarrantyClaimRecord> getClaimsBySerial(String serialNumber) {
        return claimRepo.findBySerialNumber(serialNumber);
    }

    @Override
    public List<WarrantyClaimRecord> getAllClaims() {
        return claimRepo.findAll();
    }
}
