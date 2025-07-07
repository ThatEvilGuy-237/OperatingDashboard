package com.evil.backend.user.entity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class JobTitle {
    @Id
    @GeneratedValue
    private Long id;
    // user information
    @Column(nullable = false, unique = true, length = 100)
    private String title;
    
    @Column(columnDefinition = "TEXT")
    private String emote;
}
