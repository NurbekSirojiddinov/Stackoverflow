package com.example.stackoverflow.config;

import com.example.stackoverflow.entity.UserEntity;
import com.example.stackoverflow.repository.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

@Configuration
public class JpaAuditingConfig {

    private final UserRepository repository;

    public JpaAuditingConfig(UserRepository repository) {
        this.repository = repository;
    }

    @Bean
    public AuditorAware<UserEntity> userEntityAuditorAware() {
        return () -> {
            SecurityContext securityContext = SecurityContextHolder.getContext();
            if (securityContext != null && securityContext.getAuthentication() != null) {
                return repository.findByEmail(securityContext.getAuthentication().getName());
            }
            return Optional.empty();
        };
    }
}
