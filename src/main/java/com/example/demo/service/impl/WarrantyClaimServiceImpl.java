package com.example.demo.service.impl;

import com.example.demo.model.*;
import com.example.demo.repository.*;
import com.example.demo.service.WarrantyClaimService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class WarrantyClaimServiceImpl implements WarrantyClaimService {

    private final WarrantyClaimRecordRepository claimRepo;
    private final DeviceOwnershipRecordRepository deviceRepo;
    private final StolenDeviceReportRepository stolenRepo;

    public WarrantyClaimServiceImpl(
            WarrantyClaimRecordRepository claimRepo,
            DeviceOwnershipRecordRepository deviceRepo,
            StolenDeviceReportRepository stolenRepo
    ) {
        this.claimRepo = claimRepo;
        this.deviceRepo = deviceRepo;
        this.stolenRepo = stolenRepo;
    }

    @Override
    public WarrantyClaimRecord submitClaim(WarrantyClaimRecord claim) {

        DeviceOwnershipRecord device = deviceRepo
                .findBySerialNumber(claim.getSerialNumber())
                .orElseThrow(() -> new NoSuchElementException("Offer not found"));

        claim.setDevice(device);
        claim.setStatus("PENDING");

        if (!device.isActive()) {
            claim.setStatus("FLAGGED");
        }

        if (device.getWarrantyExpiration().isBefore(LocalDate.now())) {
            claim.setStatus("FLAGGED");
        }

        if (stolenRepo.existsBySerialNumber(device.getSerialNumber())) {
            claim.setStatus("FLAGGED");
        }

        if (claimRepo.existsBySerialNumberAndClaimReason(
                device.getSerialNumber(), claim.getClaimReason())) {
            claim.setStatus("FLAGGED");
        }

        return claimRepo.save(claim);
    }

    @Override
    public WarrantyClaimRecord updateClaimStatus(Long id, String status) {
        WarrantyClaimRecord claim = getClaimById(id);
        claim.setStatus(status);
        return claimRepo.save(claim);
    }

    @Override
    public WarrantyClaimRecord getClaimById(Long id) {
        return claimRepo.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Request not found"));
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