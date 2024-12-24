package com.example.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.project.entity.EmailInquiry;
import com.example.project.repository.EmailInquiryRepository;

@Service
public class EmailService {

    @Autowired
    private EmailInquiryRepository emailInquiryRepository; // 이메일 문의 데이터를 저장하는 Repository

    // 이메일 문의 저장
    public void save(EmailInquiry emailInquiry) {
        emailInquiryRepository.save(emailInquiry); // EmailInquiry 객체를 저장
    }

}
