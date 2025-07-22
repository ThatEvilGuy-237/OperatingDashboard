package com.evil.backend.core.database;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.evil.backend.user.entity.Account;
import com.evil.backend.user.entity.JobTitle;
import com.evil.backend.user.entity.Privilege;
import com.evil.backend.user.entity.PrivilegeType;
import com.evil.backend.user.entity.Role;
import com.evil.backend.user.repository.AccountRepository;
import com.evil.backend.user.repository.JobTitleRepository;
import com.evil.backend.user.repository.PrivilegeRepository;
import com.evil.backend.user.repository.RoleRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class DatabaseSeeder implements CommandLineRunner {

    private final PrivilegeRepository privilegeRepository;
    private final RoleRepository roleRepository;
    private final AccountRepository accountRepository;
    private final JobTitleRepository jobTitleRepository;

    @Override
    public void run(String... args) {
        seedUsers();
    }

    private void seedUsers() {
        if (accountRepository.count() > 0) {
            return;
        }

        // Create privileges
        Privilege readPrivilege = new Privilege(null, "READ_ACCESS", PrivilegeType.READ_ACCESS, "Allows reading resources");
        Privilege writePrivilege = new Privilege(null, "Can Edit Data", PrivilegeType.WRITE_ACCESS, "Allows modifying resources");
        privilegeRepository.saveAll(List.of(readPrivilege, writePrivilege));

        // Create roles
        Role adminRole = new Role(null, "ADMIN", "#FF0000", Set.of(readPrivilege, writePrivilege),100);
        Role userRole = new Role(null, "USER", "#00FF00", Set.of(readPrivilege), 200);
        roleRepository.saveAll(List.of(adminRole, userRole));

        // Create job titles
        JobTitle adminJob = new JobTitle(null, "Administrator", "🧠");
        JobTitle userJob = new JobTitle(null, "Basic User", "👤");
        jobTitleRepository.saveAll(List.of(adminJob, userJob));

        // Create accounts
        Account admin = Account.builder()
                .username("admin")
                .email("admin@example.com")
                .firstName("Jeff")
                .lastName("The Admin")
                .phoneNumber("1234567890")
                .password("admin")
                .roles(Set.of(adminRole, userRole))
                .validated(false)
                .locked(false)
                .jobTitle(adminJob)
                .accountCreated(LocalDateTime.now())
                .lastLogin(null)
                .build();

        Account user = Account.builder()
                .username("user")
                .email("user@example.com")
                .firstName("Tom")
                .lastName("The User")
                .phoneNumber("0987654321")
                .password("user")
                .roles(Set.of(userRole))
                .validated(false)
                .locked(false)
                .jobTitle(userJob)
                .accountCreated(LocalDateTime.now())
                .lastLogin(null)
                .build();

        accountRepository.saveAll(List.of(admin, user));
    }
}
