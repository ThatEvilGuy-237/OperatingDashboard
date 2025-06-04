package com.evil.backend.xsecurity;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.evil.backend.user.entity.Account;
import com.evil.backend.user.repository.AccountRepository;

@Service
public class SecurityUserDetailsService implements UserDetailsService {

    private final AccountRepository accountRepository;

    public SecurityUserDetailsService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String identifier) throws UsernameNotFoundException {
        Account account = accountRepository
        .findByUsernameOrEmail(identifier, identifier)
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + identifier));

        return new SecurityUserDetails(account);
    }

}         