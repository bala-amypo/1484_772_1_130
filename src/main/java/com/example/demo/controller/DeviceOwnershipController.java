package com.example.demo.controller;

import com.example.demo.model.DeviceOwnershipRecord;
import com.example.demo.service.DeviceOwnershipService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/devices")
public class DeviceOwnershipController {

    private final DeviceOwnershipService service;

    public DeviceOwnershipController(DeviceOwnershipService service) {
        this.service = service;
    }

    @PostMapping
    public DeviceOwnershipRecord registerDevice(
            @RequestBody DeviceOwnershipRecord device) {
        return service.registerDevice(device);
    }

    @PutMapping("/{id}/status")
    public void updateStatus(
            @PathVariable Long id,
            @RequestParam boolean active) {
        service.updateDeviceStatus(id, active);
    }

    @GetMapping("/serial/{serialNumber}")
    public DeviceOwnershipRecord getBySerial(
            @PathVariable String serialNumber) {
        return service.getBySerial(serialNumber);
    }

    @GetMapping
    public List<DeviceOwnershipRecord> getAllDevices() {
        return service.getAllDevices();
    }
}
