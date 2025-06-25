package com.acleda.student.config;

import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class LoggingFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain) throws ServletException, IOException {

        // Wrap the request and response to allow multiple reads of their bodies
        ContentCachingRequestWrapper wrappedRequest = new ContentCachingRequestWrapper(request);
        ContentCachingResponseWrapper wrappedResponse = new ContentCachingResponseWrapper(response);

        // Continue the filter chain with the wrapped request/response
        filterChain.doFilter(wrappedRequest, wrappedResponse);

        // Read the request/response body from the cached wrapper
        String requestBody = new String(wrappedRequest.getContentAsByteArray(), request.getCharacterEncoding());
        String responseBody = new String(wrappedResponse.getContentAsByteArray(), response.getCharacterEncoding());

        // Get the HTTP status of the response
        HttpStatus status = HttpStatus.valueOf(wrappedResponse.getStatus());

        // Log request and response based on status (error or not)
        // NOTE: If you configure AsyncAppender in logback.xml, these log statements
        // will be handled asynchronously
        if (status.isError()) {
            log.error("Request {} {} {}", request.getMethod(), request.getRequestURI(), requestBody);
            log.error("Response {} {}", status, responseBody);
        } else {
            log.info("Request {} {} {}", request.getMethod(), request.getRequestURI(), requestBody);
            log.info("Response {} {}", status, responseBody);
        }

        // Important: copy the cached response body back to the actual response
        wrappedResponse.copyBodyToResponse();
    }
}