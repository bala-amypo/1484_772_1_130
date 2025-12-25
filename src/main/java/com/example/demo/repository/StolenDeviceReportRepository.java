package com.example.demo.repository;

import com.example.demo.model.StolenDeviceReport;

import java.util.List;
import java.util.Optional;

public interface StolenDeviceReportRepository {
    StolenDeviceReport save(StolenDeviceReport r);
    List<StolenDeviceReport> findAll();
    List<StolenDeviceReport> findBySerialNumber(String serial);
    Optional<StolenDeviceReport> findById(Long id);
    boolean existsBySerialNumber(String serial);
}
