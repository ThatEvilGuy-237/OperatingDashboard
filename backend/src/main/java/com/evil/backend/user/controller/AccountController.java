package com.evil.backend.user.controller;

import com.evil.backend.user.entity.Account;
import com.evil.backend.user.service.AccountService;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.data.domain.Page;

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
    public ResponseEntity<Page<Account>> getAllUsers(
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "100") int size) {

        Page<Account> usersPage = accountService.getAllUsers(page, size);
        
        return ResponseEntity.ok(usersPage);
    }
}