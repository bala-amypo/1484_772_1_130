package com.example.demo.controller;

import com.example.demo.service.WarrantyClaimService;

public class WarrantyClaimController {
    private final WarrantyClaimService warrantyClaimService;

    public WarrantyClaimController(WarrantyClaimService warrantyClaimService) {
        this.warrantyClaimService = warrantyClaimService;
    }
}
