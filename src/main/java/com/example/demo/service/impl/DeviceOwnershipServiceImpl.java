package com.example.demo.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.model.DeviceOwnershipRecord;
import com.example.demo.repository.DeviceOwnershipRecordRepository;
import com.example.demo.service.DeviceOwnershipService;

@Service
public class DeviceOwnershipServiceImpl implements DeviceOwnershipService {

    private final DeviceOwnershipRecordRepository repository;

    public DeviceOwnershipServiceImpl(DeviceOwnershipRecordRepository repository) {
        this.repository = repository;
    }

    @Override
    public DeviceOwnershipRecord registerDevice(DeviceOwnershipRecord device) {

        if (repository.existsBySerialNumber(device.getSerialNumber())) {
            throw new RuntimeException("Device already exists with this serial number");
        }

        return repository.save(device);
    }

    @Override
    public Optional<DeviceOwnershipRecord> getBySerial(String serial) {
        return repository.findBySerialNumber(serial);
    }

    @Override
    public List<DeviceOwnershipRecord> getAllDevices() {
        return repository.findAll();
    }

    @Override
    public DeviceOwnershipRecord updateDeviceStatus(Long id, boolean active) {

        DeviceOwnershipRecord device = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Device not found with id: " + id));

        device.setActive(active);
        return repository.save(device); 
    }
}