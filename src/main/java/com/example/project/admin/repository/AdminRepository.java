package com.example.project.admin.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.GrantedAuthority;

import com.example.project.admin.Entity.Admin;

import java.util.Collection;
import java.util.List;

public interface AdminRepository extends JpaRepository<Admin, Long> {

   Optional<Admin> findByUserId(String userId);

}
