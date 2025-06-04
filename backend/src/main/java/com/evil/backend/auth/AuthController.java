package com.evil.backend.auth;

import java.util.Map;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.evil.backend.core.util.JwtUtil;
import com.evil.backend.user.entity.Account;
import com.evil.backend.user.repository.AccountRepository;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final AccountRepository accountRepository;

    public AuthController(AuthenticationManager authenticationManager, JwtUtil jwtUtil, AccountRepository accountRepository) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        this.accountRepository = accountRepository;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestParam String identifier, @RequestParam String password) {
        // identifier can be username or email
        Optional<Account> accountOpt = accountRepository.findByUsername(identifier);
        if (accountOpt.isEmpty()) {
            accountOpt = accountRepository.findByEmail(identifier);
            if (accountOpt.isEmpty()) {
                return ResponseEntity.status(401).body("Invalid credentials");
            }
        }
        Account account = accountOpt.get();

        try {
            Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(account.getUsername(), password)
            );
            String token = jwtUtil.generateToken(account);
            return ResponseEntity.ok(Map.of("token", token));
        } catch (AuthenticationException ex) {
            return ResponseEntity.status(401).body("Invalid credentials");
        }
    }
}