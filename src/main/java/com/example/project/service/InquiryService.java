package com.example.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.project.entity.Inquiry;
import com.example.project.repository.InquiryRepository;

public class InquiryService {

    private final InquiryRepository inquiryRepository;

    @Autowired
    public InquiryService(InquiryRepository inquiryRepository) {
        this.inquiryRepository = inquiryRepository;
    }

    // 모든 문의 내역을 가져오는 메소드
    public List<Inquiry> getAllInquiries() {
        return inquiryRepository.findAll();
    }

    // 특정 id로 문의를 가져오는 메소드
    public Inquiry getInquiryById(Long id) {
        return inquiryRepository.findById(id).orElse(null);
    }

    // 새 문의 사항을 추가하는 메소드
    public Inquiry saveInquiry(Inquiry inquiry) {
        return inquiryRepository.save(inquiry);
    }

    // 특정 id로 문의 사항을 삭제하는 메소드
    public void deleteInquiry(Long id) {
        inquiryRepository.deleteById(id);
    }
}
