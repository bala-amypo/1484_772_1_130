package com.example.demo.controller;

import com.example.demo.model.StolenDeviceReport;
import com.example.demo.service.StolenDeviceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/stolen-devices")
public class StolenDeviceController {

    private final StolenDeviceService stolenService;

    public StolenDeviceController(StolenDeviceService stolenService) {
        this.stolenService = stolenService;
    }

    // POST /api/stolen-devices (ADMIN)
    @PostMapping
    public ResponseEntity<?> reportStolen(@RequestBody StolenDeviceReport report) {
        return ResponseEntity.status(201).body(stolenService.reportStolen(report));
    }

    // GET /api/stolen-devices
    @GetMapping
    public ResponseEntity<?> getAllReports() {
        return ResponseEntity.ok(stolenService.getAllReports());
    }

    // GET /api/stolen-devices/{id}
    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        return ResponseEntity.ok(stolenService.getReportById(id));
    }

    // GET /api/stolen-devices/serial/{serialNumber}
    @GetMapping("/serial/{serialNumber}")
    public ResponseEntity<?> getBySerial(@PathVariable String serialNumber) {
        return ResponseEntity.ok(stolenService.getReportsBySerial(serialNumber));
    }
}