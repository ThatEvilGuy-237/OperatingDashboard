package com.evil.backend.user.controller;

import com.evil.backend.user.entity.Account;
import com.evil.backend.user.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/accounts")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @GetMapping("/{id}")
    public ResponseEntity<Account> getUserById(@PathVariable Long id) {
        return accountService.getUserById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<Account>> getAllUsers() {
        List<Account> users = accountService.getAllUsers();
        return ResponseEntity.ok(users);
    }
}