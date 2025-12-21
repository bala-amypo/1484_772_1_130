package com.example.demo.controller;

import com.example.demo.model.DeviceOwnershipRecord;
import com.example.demo.service.DeviceOwnershipService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/devices")
public class DeviceOwnershipController {

    private final DeviceOwnershipService deviceService;

    public DeviceOwnershipController(DeviceOwnershipService deviceService) {
        this.deviceService = deviceService;
    }

    // POST /api/devices (ADMIN)
    @PostMapping
    public ResponseEntity<?> registerDevice(@RequestBody DeviceOwnershipRecord device) {
        return ResponseEntity.status(201).body(deviceService.registerDevice(device));
    }

    // GET /api/devices
    @GetMapping
    public ResponseEntity<?> getAllDevices() {
        return ResponseEntity.ok(deviceService.getAllDevices());
    }

    // GET /api/devices/{id}
    @GetMapping("/{id}")
    public ResponseEntity<?> getDeviceById(@PathVariable Long id) {
        return ResponseEntity.ok(deviceService.updateDeviceStatus(id, true));
    }

    // GET /api/devices/serial/{serialNumber}
    @GetMapping("/serial/{serialNumber}")
    public ResponseEntity<?> getBySerial(@PathVariable String serialNumber) {
        return ResponseEntity.ok(deviceService.getBySerial(serialNumber));
    }

    // PUT /api/devices/{id}/status (ADMIN)
    @PutMapping("/{id}/status")
    public ResponseEntity<?> updateStatus(
            @PathVariable Long id,
            @RequestParam boolean active
    ) {
        return ResponseEntity.ok(deviceService.updateDeviceStatus(id, active));
    }
}