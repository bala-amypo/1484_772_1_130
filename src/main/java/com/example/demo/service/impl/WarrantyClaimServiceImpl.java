package com.example.demo.service.impl;

import com.example.demo.model.DeviceOwnershipRecord;
import com.example.demo.model.FraudAlertRecord;
import com.example.demo.model.WarrantyClaimRecord;
import com.example.demo.repository.DeviceOwnershipRecordRepository;
import com.example.demo.repository.FraudAlertRecordRepository;
import com.example.demo.repository.FraudRuleRepository;
import com.example.demo.repository.StolenDeviceReportRepository;
import com.example.demo.repository.WarrantyClaimRecordRepository;
import com.example.demo.service.WarrantyClaimService;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class WarrantyClaimServiceImpl implements WarrantyClaimService {

    private final WarrantyClaimRecordRepository claimRepository;
    private final DeviceOwnershipRecordRepository deviceRepository;
    private final StolenDeviceReportRepository stolenRepository;
    private final FraudAlertRecordRepository fraudAlertRepository;
    private final FraudRuleRepository fraudRuleRepository;

    public WarrantyClaimServiceImpl(
            WarrantyClaimRecordRepository claimRepository,
            DeviceOwnershipRecordRepository deviceRepository,
            StolenDeviceReportRepository stolenRepository,
            FraudAlertRecordRepository fraudAlertRepository,
            FraudRuleRepository fraudRuleRepository
    ) {
        this.claimRepository = claimRepository;
        this.deviceRepository = deviceRepository;
        this.stolenRepository = stolenRepository;
        this.fraudAlertRepository = fraudAlertRepository;
        this.fraudRuleRepository = fraudRuleRepository;
    }

    /* ================= SUBMIT CLAIM ================= */

    @Override
    public WarrantyClaimRecord submitClaim(WarrantyClaimRecord claim) {

        DeviceOwnershipRecord device = deviceRepository
                .findBySerialNumber(claim.getSerialNumber())
                .orElseThrow(() ->
                        new NoSuchElementException("Offer not found")
                );

        claim.setDevice(device);

        boolean flagged = false;

        // Check if device is stolen
        if (stolenRepository.existsBySerialNumber(claim.getSerialNumber())) {
            flagged = true;
        }

        // Check if warranty expired
        if (device.getWarrantyExpiration().isBefore(LocalDate.now())) {
            flagged = true;
        }

        // Check duplicate claim reason
        if (claimRepository.existsBySerialNumberAndClaimReason(
                claim.getSerialNumber(),
                claim.getClaimReason()
        )) {
            flagged = true;
        }

        if (flagged) {
            claim.setStatus("FLAGGED");
        } else {
            claim.setStatus("PENDING");
        }

        WarrantyClaimRecord savedClaim = claimRepository.save(claim);

        if (flagged) {
            FraudAlertRecord alert = new FraudAlertRecord();
            alert.setClaim(savedClaim);
            alert.setAlertType("CLAIM_FRAUD");
            alert.setSeverity("HIGH");
            alert.setMessage("Warranty claim flagged for fraud review");
            fraudAlertRepository.save(alert);
        }

        return savedClaim;
    }

    /* ================= UPDATE CLAIM STATUS ================= */

    @Override
    public WarrantyClaimRecord updateClaimStatus(Long claimId, String status) {

        WarrantyClaimRecord claim = claimRepository.findById(claimId)
                .orElseThrow(() ->
                        new NoSuchElementException("Request not found")
                );

        claim.setStatus(status);
        return claimRepository.save(claim);
    }

    /* ================= GET CLAIM BY ID ================= */

    @Override
    public Optional<WarrantyClaimRecord> getClaimById(Long id) {
        return claimRepository.findById(id);
    }

    /* ================= GET CLAIMS BY SERIAL ================= */

    @Override
    public List<WarrantyClaimRecord> getClaimsBySerial(String serialNumber) {
        return claimRepository.findBySerialNumber(serialNumber);
    }

    /* ================= GET ALL CLAIMS ================= */

    @Override
    public List<WarrantyClaimRecord> getAllClaims() {
        return claimRepository.findAll();
    }
}
