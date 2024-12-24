package com.example.project.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.project.entity.Counseling;
<<<<<<< HEAD
import com.example.project.entity.Inquiry;
import com.example.project.repository.CounselingRepository;
import com.example.project.repository.InquiryRepository;
=======
import com.example.project.repository.CounselingRepository;
>>>>>>> 560831b48a02e4345a2370324d58d0c8cdf674ab

@Controller
public class CounselingController {

    @Autowired
    private CounselingRepository counselingRepository;

<<<<<<< HEAD
    @Autowired
    private InquiryRepository inquiryRepository;

    @GetMapping("/center/counseling/history")
    public String getCounselingHistory(@RequestParam(defaultValue = "1") int page, Model model) {
        int pageSize = 10;

        // 총 페이지 수 계산
        Pageable pageable = PageRequest.of(page - 1, pageSize);
        Page<Counseling> counselingsPage = counselingRepository.findAll(pageable);

        // 이메일 문의 데이터 조회
        List<Inquiry> inquiries = inquiryRepository.findAll();

        // 상담 내역에 이메일 문의 데이터를 통합
        List<Object> combinedList = new ArrayList<>(counselingsPage.getContent());
        combinedList.addAll(inquiries);

        // 모델에 데이터 추가
        model.addAttribute("counselings", combinedList);
        model.addAttribute("totalPages", counselingsPage.getTotalPages());
        model.addAttribute("currentPage", page);

        return "/center/counselingHistory";
    }

    @GetMapping("/center/counseling")
    public String showCounselingPage(Model model) {
        Inquiry counseling = new Inquiry();
        counseling.setCounselingType("Type A"); // set the counseling type
        model.addAttribute("counseling", counseling);

        return "center/counselingPage";
    }

=======
    @GetMapping("/center/counseling")
    public String getCounselingHistory(@RequestParam(defaultValue = "1") int page, Model model) {

        // 이메일 문의 내역 조회
        List<Counseling> counselings = counselingRepository.findAll(); // 상담 내역 조회

        // 예시: 페이지당 항목 수 (예: 10개)
        int pageSize = 10;
        int totalCounselings = counselings.size(); // 전체 상담 내역의 개수
        int totalPages = (int) Math.ceil((double) totalCounselings / pageSize); // 총 페이지 수 계산

        // 상담 내역 페이징 처리
        Pageable pageable = PageRequest.of(page - 1, pageSize);
        Page<Counseling> counselingsPage = counselingRepository.findAll(pageable);

        // 이메일 문의 내역을 포함하여 상담 내역 추가
        model.addAttribute("counselings", counselingsPage.getContent());
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("cuurentPage", page);

        return "/center/counseling"; // counseling.html로 상담 내역을 전달
    }
>>>>>>> 560831b48a02e4345a2370324d58d0c8cdf674ab
}
