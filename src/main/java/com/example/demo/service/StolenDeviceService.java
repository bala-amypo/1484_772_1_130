package com.example.demo.service;

import com.example.demo.model.StolenDevice;

import java.util.List;

public interface StolenDeviceService {

    StolenDevice reportStolen(StolenDevice device);

    List<StolenDevice> getReportsBySerial(String serialNumber);

    StolenDevice getReportById(Long id);

    List<StolenDevice> getAllReports();
}
