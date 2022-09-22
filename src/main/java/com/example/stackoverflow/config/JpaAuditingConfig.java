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
public class JpaAuditingConfig implements AuditorAware<UserEntity>{

    private final UserRepository userRepository;

    public JpaAuditingConfig(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Bean
    public AuditorAware<UserEntity> userEntityAuditorAware() {
        return () -> {
            SecurityContext securityContext = SecurityContextHolder.getContext();
            if (securityContext != null && securityContext.getAuthentication() != null) {
                return userRepository.findByUsername(securityContext.getAuthentication().getPrincipal().toString());
            }
            return Optional.empty();
        };
    }

    @Override
    public Optional<UserEntity> getCurrentAuditor() {
        return userEntityAuditorAware().getCurrentAuditor();
    }
}
