package com.example.stackoverflow.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

@Configuration
public class JpaAuditingConfig {
    @Bean
    public AuditorAware<String> userEntityAuditorAware() {
        return () -> {
            SecurityContext securityContext = SecurityContextHolder.getContext();
            if (securityContext != null && securityContext.getAuthentication() != null) {
                return Optional.ofNullable(securityContext.getAuthentication().getName());
            }
            return Optional.empty();
        };
    }
}
