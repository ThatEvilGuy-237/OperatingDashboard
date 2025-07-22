package com.evil.backend.user.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.evil.backend.user.entity.Role;
import com.evil.backend.user.repository.RoleRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RoleService {
    
    private final RoleRepository roleRepository;
    public List<Role> getAllRoles() {
        List<Role> roles = new ArrayList<>();
        roleRepository.findAllByOrderByPositionAsc().forEach(roles::add);
        return roles;
    }
}
