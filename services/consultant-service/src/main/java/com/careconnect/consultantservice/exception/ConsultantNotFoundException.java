package com.careconnect.consultantservice.exception;

public class ConsultantNotFoundException extends RuntimeException {
    public ConsultantNotFoundException(String message) {
        super(message);
    }
}
