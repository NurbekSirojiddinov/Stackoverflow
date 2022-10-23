package com.example.stackoverflow;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@SpringBootApplication
//@EnableJpaRepositories
@EnableSwagger2
@EnableJpaAuditing(auditorAwareRef="userEntityAuditorAware")
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class StackOverflowApplication {
    public static void main(String[] args) {
        SpringApplication.run(StackOverflowApplication.class, args);
    }
}
