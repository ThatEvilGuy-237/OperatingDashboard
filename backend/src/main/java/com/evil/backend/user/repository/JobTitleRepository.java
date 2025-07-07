package com.evil.backend.user.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.evil.backend.user.entity.JobTitle;

public interface JobTitleRepository extends JpaRepository<JobTitle, Long> {
    Optional<JobTitle> findDtoById(Long id);
    Optional<JobTitle> findByTitle(String title);
}
