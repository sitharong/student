package com.acleda.student.config;

import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import com.acleda.student.response.ResponseService;

import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @Autowired
    private ResponseService responseService;

    /**
     * Utility method to build a consistent error response using ResponseService.
     */
    private ResponseEntity<Object> handler(Object error, HttpStatus status) {
        return ResponseEntity.status(status).body(responseService.error(error, status));
    }

    /**
     * Handles validation errors for @Valid annotated request bodies.
     * Returns a map of field names to error messages.
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        var errors = e.getBindingResult().getFieldErrors().stream()
                .collect(Collectors.toMap(
                        fe -> fe.getField(),
                        fe -> fe.getDefaultMessage(),
                        (msg1, msg2) -> msg1)); // In case of duplicate keys, keep the first message
        return handler(errors, HttpStatus.BAD_REQUEST);
    }

    /**
     * Handles validation errors for @Validated annotated parameters (e.g., in query
     * params or path variables).
     * Returns a map of property paths to error messages.
     */
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Object> handleConstraintViolationException(ConstraintViolationException e) {
        var errors = e.getConstraintViolations().stream()
                .collect(Collectors.toMap(
                        cv -> cv.getPropertyPath().toString(),
                        cv -> cv.getMessage(),
                        (msg1, msg2) -> msg1)); // In case of duplicate keys, keep the first message
        return handler(errors, HttpStatus.BAD_REQUEST);
    }

    /**
     * Handles cases where a requested resource is not found.
     */
    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<Object> handleNoSuchElementException(NoSuchElementException e) {
        return handler(e.getMessage(), HttpStatus.NOT_FOUND);
    }

    /**
     * Handles database constraint violations (e.g., duplicate keys).
     * Returns the root cause message if available.
     */
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<Object> handleDataIntegrityViolationException(DataIntegrityViolationException e) {
        var error = Optional.ofNullable(e.getRootCause())
                .map(Throwable::getMessage)
                .orElse(e.getMessage());
        return handler(error, HttpStatus.CONFLICT);
    }

    /**
     * Handles all other uncaught exceptions.
     * Logs the stack trace and returns a generic error message to the client.
     * 
     * We use a generic message ("Internal server error") instead of e.getMessage()
     * to avoid exposing sensitive internal details or stack traces to the client.
     * The real exception is logged for diagnostics, but the client only receives
     * a safe, user-friendly message.
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleException(Exception e) {
        log.error("Unhandled exception", e);
        return handler("Internal server error", HttpStatus.INTERNAL_SERVER_ERROR);
    }
}