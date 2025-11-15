package com.careconnect.appointmentservice.exception;

public class ConsultantNotFoundException extends RuntimeException {
    public ConsultantNotFoundException(String message) {
        super(message);
    }
}
