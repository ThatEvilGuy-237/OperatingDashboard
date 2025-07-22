package com.evil.backend.user.controller;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.evil.backend.user.dto.AccountDto;
import com.evil.backend.user.service.AccountService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("api/accounts")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @GetMapping("/{id}")
    public ResponseEntity<AccountDto> getUserById(@PathVariable Long id) {
        AccountDto account = accountService.getUserById(id);

        return ResponseEntity.ok(account);
    }

    @GetMapping
    public ResponseEntity<Page<AccountDto>> getAllUsers(
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "100") int size) {

        Page<AccountDto> usersPage = accountService.getAllUsers(page, size);
        return ResponseEntity.ok(usersPage);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AccountDto> updateUser(@PathVariable Long id, @RequestBody AccountDto accountDto) {
        AccountDto updatedAccount = accountService.updateUser(id, accountDto);
        return ResponseEntity.ok(updatedAccount);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<AccountDto> deleteUser(@PathVariable Long id) {
        AccountDto deletedAccount = accountService.deleteUser(id);
        return ResponseEntity.ok(deletedAccount);
    }
    
}