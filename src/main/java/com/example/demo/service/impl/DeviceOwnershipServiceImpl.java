package com.example.demo.service.impl;

import com.example.demo.model.DeviceOwnershipRecord;
import com.example.demo.repository.DeviceOwnershipRecordRepository;
import com.example.demo.service.DeviceOwnershipService;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

public class DeviceOwnershipServiceImpl implements DeviceOwnershipService {
    private final DeviceOwnershipRecordRepository repo;

    public DeviceOwnershipServiceImpl(DeviceOwnershipRecordRepository repo) {
        this.repo = repo;
    }

    @Override
    public DeviceOwnershipRecord registerDevice(DeviceOwnershipRecord r) {
        if (r == null || r.getSerialNumber() == null) throw new IllegalArgumentException("serial required");
        if (repo.existsBySerialNumber(r.getSerialNumber())) throw new IllegalArgumentException("duplicate");
        if (r.getActive() == null) r.setActive(true);
        return repo.save(r);
    }

    @Override
    public Optional<DeviceOwnershipRecord> getBySerial(String serial) {
        return repo.findBySerialNumber(serial);
    }

    @Override
    public List<DeviceOwnershipRecord> getAllDevices() {
        return repo.findAll();
    }

    @Override
    public DeviceOwnershipRecord updateDeviceStatus(Long id, boolean active) {
        DeviceOwnershipRecord d = repo.findById(id).orElseThrow(NoSuchElementException::new);
        d.setActive(active);
        return repo.save(d);
    }
}
