package com.example.demo.controller;

import com.example.demo.service.DeviceOwnershipService;

public class DeviceOwnershipController {
    private final DeviceOwnershipService deviceOwnershipService;

    public DeviceOwnershipController(DeviceOwnershipService deviceOwnershipService) {
        this.deviceOwnershipService = deviceOwnershipService;
    }
}
