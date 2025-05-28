package com.evil.backend.core.database;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = "com.evil.backend.user.repository")
public class DatabaseConfig {
    // Additional JPA or DataSource configuration can be added here if needed.
}