package com.example.demo.controller;

import com.example.demo.model.StolenDeviceReport;
import com.example.demo.service.StolenDeviceService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/stolen-devices")
public class StolenDeviceController {

    private final StolenDeviceService stolenDeviceService;

    public StolenDeviceController(StolenDeviceService stolenDeviceService) {
        this.stolenDeviceService = stolenDeviceService;
    }

    /* ================= REPORT STOLEN DEVICE ================= */
    /* ADMIN ONLY */

    @PostMapping
    public ResponseEntity<StolenDeviceReport> reportStolenDevice(
            @RequestBody StolenDeviceReport report
    ) {
        StolenDeviceReport savedReport =
                stolenDeviceService.reportStolen(report);
        return ResponseEntity.ok(savedReport);
    }

    /* ================= GET ALL STOLEN REPORTS ================= */
    /* PROTECTED */

    @GetMapping
    public ResponseEntity<List<StolenDeviceReport>> getAllReports() {
        return ResponseEntity.ok(
                stolenDeviceService.getAllReports()
        );
    }

    /* ================= GET REPORT BY ID ================= */
    /* PROTECTED */

    @GetMapping("/{id}")
    public ResponseEntity<StolenDeviceReport> getReportById(
            @PathVariable Long id
    ) {
        Optional<StolenDeviceReport> report =
                stolenDeviceService.getReportById(id);

        return report
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /* ================= GET REPORTS BY SERIAL ================= */
    /* PROTECTED */

    @GetMapping("/serial/{serialNumber}")
    public ResponseEntity<List<StolenDeviceReport>> getReportsBySerial(
            @PathVariable String serialNumber
    ) {
        return ResponseEntity.ok(
                stolenDeviceService.getReportsBySerial(serialNumber)
        );
    }
}
