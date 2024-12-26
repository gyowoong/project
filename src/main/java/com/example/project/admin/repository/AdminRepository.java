package com.example.project.admin.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.project.admin.Entity.Admin;

public interface AdminRepository extends JpaRepository<Admin, Long> {

   Optional<Admin> findByUserId(String username);

}
