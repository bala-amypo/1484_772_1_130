package com.example.demo.repository;

import com.example.demo.model.DeviceOwnershipRecord;

import java.util.List;
import java.util.Optional;

public interface DeviceOwnershipRecordRepository {
    DeviceOwnershipRecord save(DeviceOwnershipRecord r);
    boolean existsBySerialNumber(String serial);
    Optional<DeviceOwnershipRecord> findBySerialNumber(String serial);
    Optional<DeviceOwnershipRecord> findById(Long id);
    List<DeviceOwnershipRecord> findAll();
}
