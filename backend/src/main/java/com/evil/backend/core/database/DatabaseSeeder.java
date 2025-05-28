package com.evil.backend.core.database;

import com.evil.backend.user.entity.Account;
import com.evil.backend.user.entity.Privilege;
import com.evil.backend.user.entity.PrivilegeType;
import com.evil.backend.user.entity.Role;
import com.evil.backend.user.repository.AccountRepository;
import com.evil.backend.user.repository.PrivilegeRepository;
import com.evil.backend.user.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class DatabaseSeeder implements CommandLineRunner {

    private final PrivilegeRepository privilegeRepository;
    private final RoleRepository roleRepository;
    private final AccountRepository accountRepository;

    @Override
    public void run(String... args) {
        seedUsers();
    }

    private void seedUsers() {
        if (accountRepository.count() == 0) {
            // Create privileges
            Privilege readPrivilege = new Privilege(null, "READ_ACCESS", PrivilegeType.MANAGE_SERVER, "Allows read access to resources");
            Privilege writePrivilege = new Privilege(null, "WRITE_ACCESS", PrivilegeType.KICK_USERS, "Allows write access to resources");

            privilegeRepository.saveAll(List.of(readPrivilege, writePrivilege));

            // Create roles
            Role adminRole = new Role(null, "ADMIN", "#FF0000", Set.of(readPrivilege, writePrivilege));
            Role userRole = new Role(null, "USER", "#00FF00", Set.of(readPrivilege));

            roleRepository.saveAll(List.of(adminRole, userRole));

            // Create accounts
            Account admin = new Account(null, "admin", "admin@example.com", "Admin", "User", "1234567890", "password", Set.of(adminRole, userRole));
            Account user = new Account(null, "user", "user@example.com", "Regular", "User", "0987654321", "password", Set.of(userRole));

            accountRepository.saveAll(List.of(admin, user));
        }
    }
}
