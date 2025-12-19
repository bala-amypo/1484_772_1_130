package com.example.demo.controller;

import com.example.demo.model.StolenDevice;
import com.example.demo.service.StolenDeviceService;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/stolen-devices")
public class StolenDeviceController {

    private final StolenDeviceService service;

    public StolenDeviceController(StolenDeviceService service) {
        this.service = service;
    }

    @PostMapping
    public StolenDevice reportStolen(
            @Valid @RequestBody StolenDevice device) {
        return service.reportStolen(device);
    }

    @GetMapping("/serial/{serialNumber}")
    public List<StolenDevice> getBySerial(
            @PathVariable String serialNumber) {
        return service.getReportsBySerial(serialNumber);
    }

    @GetMapping("/{id}")
    public StolenDevice getById(@PathVariable Long id) {
        return service.getReportById(id);
    }

    @GetMapping
    public List<StolenDevice> getAll() {
        return service.getAllReports();
    }
}
