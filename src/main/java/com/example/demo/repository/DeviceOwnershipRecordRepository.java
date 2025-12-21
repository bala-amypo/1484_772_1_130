package com.example.demo.repository;

import com.example.demo.model.DeviceOwnershipRecord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DeviceOwnershipRecordRepository
        extends JpaRepository<DeviceOwnershipRecord, Long> {

    // Finds device by serial
    Optional<DeviceOwnershipRecord> findBySerialNumber(String serialNumber);

    // Check if device serial exists
    boolean existsBySerialNumber(String serialNumber);

    // Retrieves all devices
    List<DeviceOwnershipRecord> findAll();

    // Retrieves specific device
    Optional<DeviceOwnershipRecord> findById(Long id);
}