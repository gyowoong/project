package com.example.project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.project.entity.Counseling;

public interface CounselingRepository extends JpaRepository<Counseling, Long> {

    List<Counseling> findByUserId(Long userId);

}
