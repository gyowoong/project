package com.example.project.admin.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.project.admin.Entity.Admin;
import com.example.project.entity.test.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, String> {

   Optional<UserEntity> findByUserId(String username);

}
