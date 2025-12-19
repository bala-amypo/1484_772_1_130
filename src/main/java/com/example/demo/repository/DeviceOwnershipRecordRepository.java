package com.example.demo.repository;

import com.example.demo.model.DeviceOwnershipRecord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DeviceOwnershipRecordRepository
        extends JpaRepository<DeviceOwnershipRecord, Long> {

    // Finds device by serial number
    Optional<DeviceOwnershipRecord> findBySerialNumber(String serialNumber);

    // Checks if a serial number already exists
    boolean existsBySerialNumber(String serialNumber);
}
