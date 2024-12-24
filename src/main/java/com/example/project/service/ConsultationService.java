package com.example.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.project.entity.Consultation;
import com.example.project.repository.ConsultationRepository;

@Service
public class ConsultationService {
    private final ConsultationRepository consultationRepository;

    @Autowired
    public ConsultationService(ConsultationRepository consultationRepository) {
        this.consultationRepository = consultationRepository;
    }

    // 모든 상담 목록 조회
    public List<Consultation> getAllConsultations() {
        return consultationRepository.findAll();
    }
}
