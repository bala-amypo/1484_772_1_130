package com.example.demo.repository;

import com.example.demo.model.StolenDeviceReport;

import java.util.List;
import java.util.Optional;

public interface StolenDeviceReportRepository {
    boolean existsBySerialNumber(String s);
    List<StolenDeviceReport> findBySerialNumber(String s);
    List<StolenDeviceReport> findAll();
    StolenDeviceReport save(StolenDeviceReport s);
}
