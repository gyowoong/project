package com.example.project.admin.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.project.admin.Entity.MovieState;

public interface MovieStateRepository extends JpaRepository<MovieState, Long> {

}
