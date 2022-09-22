package com.example.stackoverflow.config;

import com.example.stackoverflow.entity.UserEntity;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.core.userdetails.UserDetails;

//@Configuration
//@EnableJpaAuditing(auditorAwareRef = "customAuditProvider")
//public class JpaConfig {
//
//    @Bean
//    public AuditorAware<UserDetails> customAuditProvider() {
//        return new JpaAuditingConfig();
//    }
//}