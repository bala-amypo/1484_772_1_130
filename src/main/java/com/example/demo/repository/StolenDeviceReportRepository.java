package com.example.demo.repository;

import com.example.demo.model.StolenDeviceReport;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface StolenDeviceReportRepository
        extends JpaRepository<StolenDeviceReport, Long> {

    // Retrieves specific report
    Optional<StolenDeviceReport> findById(Long id);

    // Retrieves all reports
    List<StolenDeviceReport> findAll();

    // Finds reports by serial
    List<StolenDeviceReport> findBySerialNumber(String serialNumber);

    // Check if device reported stolen
    boolean existsBySerialNumber(String serialNumber);
}