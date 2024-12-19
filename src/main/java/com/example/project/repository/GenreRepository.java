package com.example.project.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.project.entity.Genre;

public interface GenreRepository extends JpaRepository<Genre, Long> {
    
    
}
