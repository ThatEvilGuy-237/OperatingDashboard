package com.evil.backend.auth;

import java.util.Optional;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

import com.evil.backend.core.util.JwtUtil;
import com.evil.backend.user.entity.Account;
import com.evil.backend.user.repository.AccountRepository;

@Service
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final AccountRepository accountRepository;

    public AuthService(AuthenticationManager authenticationManager, JwtUtil jwtUtil, AccountRepository accountRepository) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        this.accountRepository = accountRepository;
    }

    public JwtDTO authenticateAndGenerateToken(AuthDTO loginDTO) throws AuthenticationException {
        String identifier = loginDTO.getIdentifier();
        String password = loginDTO.getPassword();

        Optional<Account> accountOpt = accountRepository.findByUsername(identifier);
        if (accountOpt.isEmpty()) {
            accountOpt = accountRepository.findByEmail(identifier);
            if (accountOpt.isEmpty()) {
                throw new AuthenticationException("Invalid credentials") {};
            }
        }
        Account account = accountOpt.get();

        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(account.getUsername(), password)
        );
        String token = jwtUtil.generateToken(account);
        return new JwtDTO(token);
    }
}