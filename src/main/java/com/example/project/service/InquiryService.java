package com.example.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.project.entity.Inquiry;
import com.example.project.repository.InquiryRepository;

@Service
public class InquiryService {

    @Autowired
    private InquiryRepository inquiryRepository;

    public Inquiry getInquiryById(Long id) {
        return inquiryRepository.findById(id).orElseThrow(() -> new RuntimeException("Inquiry not found"));
    }

    public void saveInquiry(Inquiry inquiry) {
        inquiryRepository.save(inquiry);
    }

    public void deleteInquiry(Long id) {
        inquiryRepository.deleteById(id);
    }

    // 모든 문의 데이터 조회
    public List<Inquiry> getAllInquiries() {
        return inquiryRepository.findAll();
    }

    // 생성자 주입
    public InquiryService(InquiryRepository inquiryRepository) {
        this.inquiryRepository = inquiryRepository;
    }

    // 새로운 문의 저장
    public void save(Inquiry inquiry) {
        inquiryRepository.save(inquiry);
    }

    // 페이지 나누기
    public List<Inquiry> getInquiries(int page) {
        Pageable pageable = PageRequest.of(page - 1, 10);
        Page<Inquiry> inquiryPage = inquiryRepository.findAll(pageable);
        return inquiryPage.getContent();
    }

    public int getTotalPages() {
        long count = inquiryRepository.count();
        return (int) Math.ceil((double) count / 10);
    }

    public void update(Inquiry inquiry) {
        Inquiry existingInquiry = inquiryRepository.findById(inquiry.getId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid inquiry ID: " + inquiry.getId()));

        existingInquiry.setName(inquiry.getName());
        existingInquiry.setEmail(inquiry.getEmail());
        existingInquiry.setContent(inquiry.getContent());

        inquiryRepository.save(existingInquiry);
    }

    public void delete(Long id) {
        if (!inquiryRepository.existsById(id)) {
            throw new IllegalArgumentException("유효하지 않는 문의 ID 입니다. " + id);
        }

        inquiryRepository.deleteById(id);
    }

    public Inquiry findById(Long id) {
        return inquiryRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Inquiry not found with id: " + id));
    }

    public List<Inquiry> getInquiry() {
        return inquiryRepository.findAll();
    }

}
