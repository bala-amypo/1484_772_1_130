package com.example.demo.repository;

import com.example.demo.model.StolenDeviceReport;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StolenDeviceReportRepository
        extends JpaRepository<StolenDeviceReport, Long> {

    // Check if a device is already reported stolen
    boolean existsBySerialNumber(String serialNumber);

    // Find stolen reports by serial number
    List<StolenDeviceReport> findBySerialNumber(String serialNumber);
}
