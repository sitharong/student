package com.acleda.student.config;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import java.util.NoSuchElementException;

@Slf4j
@ControllerAdvice
public class GlobalException {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Object methodArgumentNotValidException(MethodArgumentNotValidException e,
            HttpServletRequest request) {
        log.error("{} {} {} {}", HttpStatus.BAD_REQUEST, request.getMethod(), request.getRequestURL(),
                e.getBindingResult().getAllErrors());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getBindingResult().getAllErrors());
    }

    @ExceptionHandler(NoSuchElementException.class)
    public Object noSuchElementException(NoSuchElementException e, HttpServletRequest request) {
        log.error("{} {} {} {}", HttpStatus.NOT_FOUND, request.getMethod(), request.getRequestURL(), e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public Object exception(Exception e, HttpServletRequest request) {
        log.error("{} {} {} {}", HttpStatus.INTERNAL_SERVER_ERROR, request.getMethod(), request.getRequestURL(), e);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e);
    }
}
