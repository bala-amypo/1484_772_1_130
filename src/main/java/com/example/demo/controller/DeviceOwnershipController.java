package com.example.demo.controller;

import com.example.demo.model.DeviceOwnershipRecord;
import com.example.demo.service.DeviceOwnershipService;
import org.springframework.http.ResponseEntity;
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
    public DeviceOwnershipRecord register(@RequestBody DeviceOwnershipRecord record) {
        return service.registerDevice(record);
    }

    @GetMapping
    public List<DeviceOwnershipRecord> getAll() {
        return service.getAllDevices();
    }

    @GetMapping("/{id}")
    public DeviceOwnershipRecord getById(@PathVariable Long id) {
        return service.updateDeviceStatus(id, true);
    }

    @GetMapping("/serial/{serial}")
    public ResponseEntity<?> getBySerial(@PathVariable String serial) {
        return service.getBySerial(serial)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}/status")
    public DeviceOwnershipRecord updateStatus(@PathVariable Long id, @RequestParam boolean active) {
        return service.updateDeviceStatus(id, active);
    }
}