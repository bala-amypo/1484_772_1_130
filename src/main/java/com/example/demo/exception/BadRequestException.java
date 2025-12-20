package com.example.demo.exception;

public class BadRequestException extends IllegalArgumentException {

    public BadRequestException(String message) {
        super(message);
    }

    public static BadRequestException duplicateEmail() {
        return new BadRequestException("Email already in use");
    }

    public static BadRequestException duplicateSerial() {
        return new BadRequestException("Serial number already exists");
    }

    public static BadRequestException duplicateRule() {
        return new BadRequestException("Rule already exists");
    }

    public static BadRequestException invalidInput() {
        return new BadRequestException("Invalid input");
    }
}