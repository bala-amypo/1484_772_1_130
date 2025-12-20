package com.example.demo.exception;

import java.util.NoSuchElementException;

public class ResourceNotFoundException extends NoSuchElementException {

    public ResourceNotFoundException(String message) {
        super(message);
    }

    public static ResourceNotFoundException deviceNotFound() {
        return new ResourceNotFoundException("Device not found");
    }

    public static ResourceNotFoundException offerNotFound() {
        return new ResourceNotFoundException("Offer not found");
    }

    public static ResourceNotFoundException requestNotFound() {
        return new ResourceNotFoundException("Request not found");
    }

    public static ResourceNotFoundException matchNotFound() {
        return new ResourceNotFoundException("Match not found");
    }

    public static ResourceNotFoundException categoryNotFound() {
        return new ResourceNotFoundException("Category not found");
    }

    public static ResourceNotFoundException userNotFound() {
        return new ResourceNotFoundException("User not found");
    }
}