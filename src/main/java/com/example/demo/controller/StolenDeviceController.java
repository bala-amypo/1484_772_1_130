package com.example.demo.controller;

import com.example.demo.model.StolenDeviceReport;
import com.example.demo.service.StolenDeviceService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/stolen-devices")
public class StolenDeviceController {

    private final StolenDeviceService service;

    public StolenDeviceController(StolenDeviceService service) {
        this.service = service;
    }

    @PostMapping
    public StolenDeviceReport report(@RequestBody StolenDeviceReport report) {
        return service.reportStolen(report);
    }

    @GetMapping
    public List<StolenDeviceReport> getAll() {
        return service.getAllReports();
    }

    @GetMapping("/{id}")
    public StolenDeviceReport getById(@PathVariable Long id) {
        return service.getReportById(id).orElse(null);
    }

    @GetMapping("/serial/{serial}")
    public List<StolenDeviceReport> getBySerial(@PathVariable String serial) {
        return service.getReportsBySerial(serial);
    }
}