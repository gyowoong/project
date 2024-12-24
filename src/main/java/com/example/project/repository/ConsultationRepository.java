package com.example.project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.project.entity.Consultation;
import com.example.project.entity.ConsultationStatus;

public interface ConsultationRepository extends JpaRepository<Consultation, Long> {
    List<Consultation> findByStatus(ConsultationStatus status);
}
