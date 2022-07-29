package com.example.stackoverflow;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

import javax.persistence.EntityListeners;

@SpringBootApplication
@EntityListeners(AuditingEntityListener.class)
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class StackOverflowApplication {
    public static void main(String[] args) {
        SpringApplication.run(StackOverflowApplication.class, args);
    }
}
