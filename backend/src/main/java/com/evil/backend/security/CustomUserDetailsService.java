package com.evil.backend.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.evil.backend.user.entity.Account;
import com.evil.backend.user.repository.AccountRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final AccountRepository accountRepository;

    public CustomUserDetailsService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String identifier) throws UsernameNotFoundException {
        Account account;
        if (identifier.contains("@")) {
            // Looks like an email
            account = accountRepository.findByEmail(identifier)
                    .orElseGet(() -> accountRepository.findByUsername(identifier)
                            .orElseThrow(() -> new UsernameNotFoundException("User not found: " + identifier)));
        } else {
            // Looks like a username
            account = accountRepository.findByUsername(identifier)
                    .orElseThrow(() -> new UsernameNotFoundException("User not found: " + identifier));
        }
        return new CustomUserDetails(account);
    }
}