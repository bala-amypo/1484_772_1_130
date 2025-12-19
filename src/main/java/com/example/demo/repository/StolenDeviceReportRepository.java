package com.example.demo.repository;

import com.example.demo.model.StolenDevice;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StolenDeviceReportRepository
        extends JpaRepository<StolenDevice, Long> {

    boolean existsBySerialNumber(String serialNumber);

    List<StolenDevice> findBySerialNumber(String serialNumber);
}
