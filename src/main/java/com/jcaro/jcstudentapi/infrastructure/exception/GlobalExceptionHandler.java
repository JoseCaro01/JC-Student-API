package com.jcaro.jcstudentapi.infrastructure.exception;

import com.jcaro.jcstudentapi.application.exception.BusinessException;
import com.jcaro.jcstudentapi.application.exception.user.EmailAlreadyExistsException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.NoSuchElementException;
import java.util.Map;

/**
 * Centralized exception translation for inbound adapters.
 * Converts domain/application exceptions to structured JSON HTTP responses.
 */
@RestControllerAdvice
public class GlobalExceptionHandler {


    private ResponseEntity<Map<String, Object>> buildResponse(HttpStatus status, String message) {
        Map<String, Object> body = new HashMap<>();
        body.put("timestamp", Instant.now());
        body.put("status", status.value());
        body.put("error", status.getReasonPhrase());
        body.put("message", message);
        return new ResponseEntity<>(body, status);
    }

    // --- Business exceptions (IllegalArgumentException can be treated as business) ---
    @ExceptionHandler({BusinessException.class, IllegalArgumentException.class})
    public ResponseEntity<Map<String, Object>> handleBusiness(Exception ex) {
        final HttpStatus status = ex instanceof BusinessException ? HttpStatus.valueOf(((BusinessException) ex).getCode().toString()) : HttpStatus.BAD_REQUEST;
        return buildResponse(status, ex.getMessage());
    }

    // --- DTO validation errors ---
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handleValidation(MethodArgumentNotValidException ex) {
        StringBuilder errors = new StringBuilder();
        ex.getBindingResult().getFieldErrors().forEach(error ->
                errors.append(error.getField())
                        .append(": ")
                        .append(error.getDefaultMessage())
                        .append("; ")
        );
        return buildResponse(HttpStatus.BAD_REQUEST, errors.toString());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> handleOther(Exception ex) {
        // You can improve logging here if needed
        return buildResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Contact with the admin of the server to report this error");
    }


}
