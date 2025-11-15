package com.careconnect.consultantservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import java.time.OffsetDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ConsultantNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleNotFound(ConsultantNotFoundException ex) {
        Map<String, Object> body = new HashMap<>();
        body.put("error", "Not Found");
        body.put("message", ex.getMessage());
        body.put("status", HttpStatus.NOT_FOUND.value());
        body.put("timestamp", OffsetDateTime.now());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(body);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handleValidation(MethodArgumentNotValidException ex) {
        Map<String, Object> errors = new HashMap<>();
        for (FieldError fieldError : ex.getBindingResult().getFieldErrors()) {
            errors.put(fieldError.getField(), fieldError.getDefaultMessage());
        }
        Map<String, Object> body = new HashMap<>();
        body.put("error", "Validation Failed");
        body.put("message", errors);
        body.put("status", HttpStatus.BAD_REQUEST.value());
        body.put("timestamp", OffsetDateTime.now());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> handleGeneric(Exception ex) {
        Map<String, Object> body = new HashMap<>();
        body.put("error", "Internal Server Error");
        body.put("message", ex.getMessage());
        body.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
        body.put("timestamp", OffsetDateTime.now());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(body);
    }
}
