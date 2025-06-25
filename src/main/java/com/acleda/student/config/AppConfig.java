package com.acleda.student.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Optional;

/**
 * Application-wide configuration class.
 * Defines beans for auditing and web client usage.
 */
@Configuration
@EnableWebSecurity
public class AppConfig {

    /**
     * Provides the current auditor (username) for JPA auditing.
     * Uses the authenticated user's name from the security context.
     */
    @Bean
    public AuditorAware<String> auditorProvider() {
        return () -> Optional.of(SecurityContextHolder.getContext().getAuthentication().getName());
    }

    /**
     * Provides a singleton WebClient bean for making HTTP requests.
     */
    @Bean
    public WebClient webClient() {
        return WebClient.create();
    }

}