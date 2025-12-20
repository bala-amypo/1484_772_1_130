package com.example.demo.service.impl;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.DeviceOwnershipRecord;
import com.example.demo.model.FraudAlertRecord;
import com.example.demo.model.WarrantyClaimRecord;
import com.example.demo.repository.DeviceOwnershipRecordRepository;
import com.example.demo.repository.FraudAlertRecordRepository;
import com.example.demo.repository.FraudRuleRepository;
import com.example.demo.repository.StolenDeviceReportRepository;
import com.example.demo.repository.WarrantyClaimRecordRepository;
import com.example.demo.service.WarrantyClaimService;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class WarrantyClaimServiceImpl implements WarrantyClaimService {

    private final WarrantyClaimRecordRepository claimRepo;
    private final DeviceOwnershipRecordRepository deviceRepo;
    private final StolenDeviceReportRepository stolenRepo;
    private final FraudAlertRecordRepository alertRepo;
    private final FraudRuleRepository ruleRepo;

    public WarrantyClaimServiceImpl(
            WarrantyClaimRecordRepository claimRepo,
            DeviceOwnershipRecordRepository deviceRepo,
            StolenDeviceReportRepository stolenRepo,
            FraudAlertRecordRepository alertRepo,
            FraudRuleRepository ruleRepo
    ) {
        this.claimRepo = claimRepo;
        this.deviceRepo = deviceRepo;
        this.stolenRepo = stolenRepo;
        this.alertRepo = alertRepo;
        this.ruleRepo = ruleRepo;
    }

    @Override
    public WarrantyClaimRecord submitClaim(WarrantyClaimRecord claim) {
        String serial = claim.getDevice().getSerialNumber();

        DeviceOwnershipRecord device = deviceRepo
                .findBySerialNumber(serial)
                .orElseThrow(ResourceNotFoundException::offerNotFound);

        boolean flagged = false;

        if (stolenRepo.existsByDevice_SerialNumber(serial)) {
            flagged = true;
        }

        if (device.getWarrantyExpiration().isBefore(LocalDate.now())) {
            flagged = true;
        }

        if (claimRepo.existsByDevice_SerialNumberAndClaimReason(
                serial,
                claim.getClaimReason()
        )) {
            flagged = true;
        }

        claim.setDevice(device);
        claim.setStatus(flagged ? "FLAGGED" : "PENDING");

        WarrantyClaimRecord saved = claimRepo.save(claim);

        if (flagged) {
            FraudAlertRecord alert = new FraudAlertRecord();
            alert.setClaim(saved);
            alert.setAlertType("AUTO_FLAG");
            alert.setSeverity("HIGH");
            alert.setMessage("Claim flagged by fraud rules");
            alertRepo.save(alert);
        }

        return saved;
    }

    @Override
    public WarrantyClaimRecord updateClaimStatus(Long claimId, String status) {
        WarrantyClaimRecord claim = claimRepo
                .findById(claimId)
                .orElseThrow(ResourceNotFoundException::requestNotFound);

        claim.setStatus(status);
        return claimRepo.save(claim);
    }

    @Override
    public Optional<WarrantyClaimRecord> getClaimById(Long id) {
        return claimRepo.findById(id);
    }

    @Override
    public List<WarrantyClaimRecord> getClaimsBySerial(String serialNumber) {
        return claimRepo.findByDevice_SerialNumber(serialNumber);
    }

    @Override
    public List<WarrantyClaimRecord> getAllClaims() {
        return claimRepo.findAll();
    }
}