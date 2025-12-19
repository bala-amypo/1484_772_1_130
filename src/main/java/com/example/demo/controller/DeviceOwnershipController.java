package com.example.demo.controller;

import com.example.demo.model.DeviceOwnershipRecord;
import com.example.demo.service.DeviceOwnershipService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/devices")
public class DeviceOwnershipController {

    private final DeviceOwnershipService deviceOwnershipService;

    public DeviceOwnershipController(DeviceOwnershipService deviceOwnershipService) {
        this.deviceOwnershipService = deviceOwnershipService;
    }

    /* ================= REGISTER DEVICE ================= */
    /* ADMIN ONLY */

    @PostMapping
    public ResponseEntity<DeviceOwnershipRecord> registerDevice(
            @RequestBody DeviceOwnershipRecord device
    ) {
        DeviceOwnershipRecord savedDevice =
                deviceOwnershipService.registerDevice(device);
        return ResponseEntity.ok(savedDevice);
    }

    /* ================= GET ALL DEVICES ================= */
    /* PROTECTED */

    @GetMapping
    public ResponseEntity<List<DeviceOwnershipRecord>> getAllDevices() {
        return ResponseEntity.ok(
                deviceOwnershipService.getAllDevices()
        );
    }

    /* ================= GET DEVICE BY ID ================= */
    /* PROTECTED */

    @GetMapping("/{id}")
    public ResponseEntity<DeviceOwnershipRecord> getDeviceById(
            @PathVariable Long id
    ) {
        DeviceOwnershipRecord device =
                deviceOwnershipService.updateDeviceStatus(id, true);
        return ResponseEntity.ok(device);
    }

    /* ================= GET DEVICE BY SERIAL ================= */
    /* PROTECTED */

    @GetMapping("/serial/{serialNumber}")
    public ResponseEntity<DeviceOwnershipRecord> getDeviceBySerial(
            @PathVariable String serialNumber
    ) {
        Optional<DeviceOwnershipRecord> device =
                deviceOwnershipService.getBySerial(serialNumber);

        return device
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /* ================= UPDATE DEVICE STATUS ================= */
    /* ADMIN ONLY */

    @PutMapping("/{id}/status")
    public ResponseEntity<DeviceOwnershipRecord> updateDeviceStatus(
            @PathVariable Long id,
            @RequestParam boolean active
    ) {
        DeviceOwnershipRecord updatedDevice =
                deviceOwnershipService.updateDeviceStatus(id, active);
        return ResponseEntity.ok(updatedDevice);
    }
}
