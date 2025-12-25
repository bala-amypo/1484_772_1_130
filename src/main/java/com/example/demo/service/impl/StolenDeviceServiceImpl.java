package com.example.demo.service.impl;

import com.example.demo.model.StolenDeviceReport;
import com.example.demo.repository.DeviceOwnershipRecordRepository;
import com.example.demo.repository.StolenDeviceReportRepository;
import com.example.demo.service.StolenDeviceService;

import java.util.List;
import java.util.NoSuchElementException;

public class StolenDeviceServiceImpl implements StolenDeviceService {
    private final StolenDeviceReportRepository repo;
    private final DeviceOwnershipRecordRepository deviceRepo;

    public StolenDeviceServiceImpl(StolenDeviceReportRepository repo, DeviceOwnershipRecordRepository deviceRepo) {
        this.repo = repo;
        this.deviceRepo = deviceRepo;
    }

    @Override
    public StolenDeviceReport reportStolen(StolenDeviceReport r) {
        deviceRepo.findBySerialNumber(r.getSerialNumber()).orElseThrow(NoSuchElementException::new);
        return repo.save(r);
    }

    @Override
    public List<StolenDeviceReport> getAllReports() {
        return repo.findAll();
    }

    @Override
    public List<StolenDeviceReport> getReportsBySerial(String serial) {
        return repo.findBySerialNumber(serial);
    }
}
