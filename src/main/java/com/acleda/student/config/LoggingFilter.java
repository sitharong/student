package com.acleda.student.config;

import java.io.IOException;

import org.springframework.http.HttpStatus;
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
    protected void doFilterInternal(HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain)
            throws ServletException, IOException {

        ContentCachingRequestWrapper wrappedRequest = new ContentCachingRequestWrapper(request);
        ContentCachingResponseWrapper wrappedResponse = new ContentCachingResponseWrapper(response);

        filterChain.doFilter(wrappedRequest, wrappedResponse);

        String requestBody = new String(wrappedRequest.getContentAsByteArray(), request.getCharacterEncoding());
        String responseBody = new String(wrappedResponse.getContentAsByteArray(), response.getCharacterEncoding());

        HttpStatus status = HttpStatus.valueOf(response.getStatus());

        if (status.isError()) {
            log.error("Request {} {} {}", request.getMethod(), request.getRequestURI(), requestBody);
            log.error("Response {} {}", status, responseBody);
        } else {
            log.info("Request {} {} {}", request.getMethod(), request.getRequestURI(), requestBody);
            log.info("Response {} {}", status, responseBody);
        }

        // Important: copy content of response back to the original response
        wrappedResponse.copyBodyToResponse();
    }
}
