package com.example.project.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.project.entity.Counseling;
import com.example.project.repository.CounselingRepository;

import jakarta.transaction.Transactional;

@Service
public class CounselingService {

    private final CounselingRepository counselingRepository;

    public CounselingService(CounselingRepository counselingRepository) {
        this.counselingRepository = counselingRepository;
    }

    // 상담 내역 목록 조회
    public Page<Counseling> getCounselings(int page) {
        Pageable pageable = PageRequest.of(page - 1, 10); // 페이지 시작은 0부터
        return counselingRepository.findAll(pageable);
    }

    // 상담 저장 및 수정
    @Transactional
    public Counseling saveCounseling(Counseling counseling) {
        return counselingRepository.save(counseling);
    }

    // 상담 삭제
    @Transactional
    public void deleteCounseling(Long id) {
        counselingRepository.deleteById(id);
    }

    // 상담 상세 조회
    public Counseling getCounseling(Long id) {
        Optional<Counseling> counseling = counselingRepository.findById(id);
        return counseling.orElseThrow(() -> new IllegalArgumentException("상담을 찾을 수 없습니다."));
    }
}
