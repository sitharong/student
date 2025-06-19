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
public class GlobalExceptionHandler {

    private Object handler(Exception e, HttpServletRequest request, HttpStatus s) {
        log.error("{} {} {} {} {}", s, request.getMethod(), request.getRequestURL(),
                e.getClass(), e.getMessage());
        return ResponseEntity.status(s).body(e.getMessage());
    }

    @ExceptionHandler
    public Object methodArgumentNotValidException(MethodArgumentNotValidException e, HttpServletRequest request) {
        return handler(e, request, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public Object noSuchElementException(NoSuchElementException e, HttpServletRequest request) {
        return handler(e, request, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public Object dataIntegrityViolationException(DataIntegrityViolationException e, HttpServletRequest request) {
        return handler(e, request, HttpStatus.CONFLICT);
    }

    @ExceptionHandler
    public Object exception(Exception e, HttpServletRequest request) {
        return handler(e, request, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
