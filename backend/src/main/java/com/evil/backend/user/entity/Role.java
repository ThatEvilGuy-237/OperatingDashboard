package com.evil.backend.user.entity;

import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Role {
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false, unique = true, length = 50)
    private String name;
    
    @Column(length = 7)
    private String color;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Privilege> privileges;

    @Column(nullable = false)
    private int position;
}
