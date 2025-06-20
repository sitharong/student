package com.acleda.student.config;

import jakarta.servlet.http.HttpServletRequest;
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

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @Autowired
    private ResponseService responseService;

    private Object handler(Exception e, HttpServletRequest request, HttpStatus s) {
        // log.error("{} {} {} {} {}", s, request.getMethod(), request.getRequestURL(),
        // e.getClass(), e.getMessage());
        return ResponseEntity.status(s).body(responseService.error(e.getMessage(), s));
    }

    @ExceptionHandler({ MethodArgumentNotValidException.class, ConstraintViolationException.class })
    public Object httpStatus400(Exception e, HttpServletRequest request) {
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
