package com.acleda.student.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Optional;

@Configuration
@EnableWebSecurity
public class AppConfig {

    @Bean
    public AuditorAware<String> auditorProvider() {
        return () -> Optional.of(SecurityContextHolder.getContext().getAuthentication().getName());
    }

    @Bean
    public WebClient webClient() {
        return WebClient.create();
    }

}