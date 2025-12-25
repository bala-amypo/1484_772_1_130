package com.example.demo.service.impl;

import com.example.demo.model.DeviceOwnershipRecord;
import com.example.demo.model.FraudAlertRecord;
import com.example.demo.model.FraudRule;
import com.example.demo.model.WarrantyClaimRecord;
import com.example.demo.repository.DeviceOwnershipRecordRepository;
import com.example.demo.repository.FraudAlertRecordRepository;
import com.example.demo.repository.FraudRuleRepository;
import com.example.demo.repository.StolenDeviceReportRepository;
import com.example.demo.repository.WarrantyClaimRecordRepository;
import com.example.demo.service.WarrantyClaimService;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

public class WarrantyClaimServiceImpl implements WarrantyClaimService {
    private final WarrantyClaimRecordRepository claimRepo;
    private final DeviceOwnershipRecordRepository deviceRepo;
    private final StolenDeviceReportRepository stolenRepo;
    private final FraudAlertRecordRepository alertRepo;
    private final FraudRuleRepository ruleRepo;

    public WarrantyClaimServiceImpl(WarrantyClaimRecordRepository claimRepo,
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
    public WarrantyClaimRecord submitClaim(WarrantyClaimRecord r) {
        Optional<DeviceOwnershipRecord> deviceOpt = deviceRepo.findBySerialNumber(r.getSerialNumber());
        DeviceOwnershipRecord d = deviceOpt.orElseThrow(NoSuchElementException::new);

        boolean flag = false;
        if (claimRepo.existsBySerialNumberAndClaimReason(r.getSerialNumber(), r.getClaimReason())) {
            flag = true;
        }
        if (d.getWarrantyExpiration() != null && d.getWarrantyExpiration().isBefore(LocalDate.now())) {
            flag = true;
        }
        if (stolenRepo.existsBySerialNumber(r.getSerialNumber())) {
            flag = true;
        }
        if (flag) {
            r.setStatus("FLAGGED");
        }
        return claimRepo.save(r);
    }

    @Override
    public WarrantyClaimRecord updateClaimStatus(Long id, String status) {
        WarrantyClaimRecord c = claimRepo.findById(id).orElseThrow(NoSuchElementException::new);
        c.setStatus(status);
        return claimRepo.save(c);
    }

    @Override
    public Optional<WarrantyClaimRecord> getClaimById(Long id) {
        return claimRepo.findById(id);
    }

    @Override
    public List<WarrantyClaimRecord> getAllClaims() {
        return claimRepo.findAll();
    }

    @Override
    public List<WarrantyClaimRecord> getClaimsBySerial(String serial) {
        return claimRepo.findBySerialNumber(serial);
    }
}
