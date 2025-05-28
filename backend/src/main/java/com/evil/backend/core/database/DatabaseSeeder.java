package com.evil.backend.core.database;

import com.evil.backend.user.entity.Account;
import com.evil.backend.user.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DatabaseSeeder implements CommandLineRunner {

    private final AccountRepository accountRepository;

    @Override
    public void run(String... args) {
        // Seed example accounts if none exist
        seedUsers();
    }

    // UserSeeding
    private void seedUsers() {
        if (accountRepository.count() == 0) {
            accountRepository.save(new Account(null, "admin", "admin@example.com", "Admin", "User", "1234567890", "password", null));
        
            accountRepository.save(new Account(null, "user", "user@example.com", "User", "User", "1234567890", "password", null));
        }
    }
}