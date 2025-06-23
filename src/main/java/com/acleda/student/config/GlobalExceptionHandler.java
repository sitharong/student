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

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @Autowired
    private ResponseService responseService;

    private Object handler(Object error, HttpStatus s) {
        return ResponseEntity.status(s).body(responseService.error(error, s));
    }

    @ExceptionHandler
    public Object handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        var error = e.getBindingResult().getFieldErrors().stream()
                .map(ex -> String.format("%s %s", ex.getField(), ex.getDefaultMessage()))
                .toList();
        return handler(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public Object handleConstraintViolationException(ConstraintViolationException e) {
        var error = e.getConstraintViolations().stream()
                .map(v -> String.format("%s %s", v.getPropertyPath(), v.getMessage()))
                .toList();
        return handler(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public Object handleNoSuchElementException(NoSuchElementException e) {
        return handler(e.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public Object handleDataIntegrityViolationException(DataIntegrityViolationException e) {
        var error = Optional.ofNullable(e.getRootCause())
                .map(Throwable::getMessage)
                .orElse(e.getMessage());
        return handler(error, HttpStatus.CONFLICT);
    }

    @ExceptionHandler
    public Object handleException(Exception e) {
        return handler(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
