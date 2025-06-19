package com.acleda.student.config;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import java.util.NoSuchElementException;

@Slf4j
@ControllerAdvice
public class GlobalException {

    @ExceptionHandler
    public Object methodArgumentNotValidException(MethodArgumentNotValidException e, HttpServletRequest request) {
        log.error("{} {} {} {}", HttpStatus.BAD_REQUEST, request.getMethod(), request.getRequestURL(), e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }

    @ExceptionHandler
    public Object noSuchElementException(NoSuchElementException e, HttpServletRequest request) {
        log.error("{} {} {} {}", HttpStatus.NOT_FOUND, request.getMethod(), request.getRequestURL(), e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }

    @ExceptionHandler
    public Object dataIntegrityViolationException(DataIntegrityViolationException e, HttpServletRequest request) {
        log.error("{} {} {} {}", HttpStatus.CONFLICT, request.getMethod(), request.getRequestURL(), e.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
    }

    @ExceptionHandler
    public Object exception(Exception e, HttpServletRequest request) {
        log.error("{} {} {} {} {}", HttpStatus.INTERNAL_SERVER_ERROR, request.getMethod(), request.getRequestURL(),
                e.getClass(), e.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
    }
}
