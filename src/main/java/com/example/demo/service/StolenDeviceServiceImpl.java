package com.example.demo.service.impl;
import org.springframework.stereotype.Service;

import com.example.demo.model.StolenDevice;
import com.example.demo.repository.DeviceOwnershipRecordRepository;
import com.example.demo.repository.StolenDeviceReportRepository;
import com.example.demo.service.StolenDeviceService;

import java.util.List;
import java.util.NoSuchElementException;
@Service
public class StolenDeviceServiceImpl implements StolenDeviceService {

    private final StolenDeviceReportRepository stolenRepo;
    private final DeviceOwnershipRecordRepository deviceRepo;

    public StolenDeviceServiceImpl(
            StolenDeviceReportRepository stolenRepo,
            DeviceOwnershipRecordRepository deviceRepo) {
        this.stolenRepo = stolenRepo;
        this.deviceRepo = deviceRepo;
    }

    @Override
    public StolenDevice reportStolen(StolenDevice device) {
        if (!deviceRepo.existsBySerialNumber(device.getSerialNumber())) {
            throw new NoSuchElementException("Device not found");
        }
        return stolenRepo.save(device);
    }

    @Override
    public List<StolenDevice> getReportsBySerial(String serialNumber) {
        return stolenRepo.findBySerialNumber(serialNumber);
    }

    @Override
    public StolenDevice getReportById(Long id) {
        return stolenRepo.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Report not found"));
    }

    @Override
    public List<StolenDevice> getAllReports() {
        return stolenRepo.findAll();
    }
}
