package com.example.project.controller;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.project.entity.Counseling;
import com.example.project.service.CounselingService;

@RequestMapping("/center/counseling")
@RestController
public class CounselingController {

    private final CounselingService counselingService;

    public CounselingController(CounselingService counselingService) {
        this.counselingService = counselingService;
    }

    @GetMapping("/counseling")
    public String showCounselingPage(Model model) {
        // 여기에 필요한 상담 데이터를 model에 추가하거나 로직을 처리합니다.
        model.addAttribute("counselings", counselingService.getCounselingList()); // 예시
        return "counseling"; // counseling.html 페이지로 반환
    }

    // 상담 내역 조회
    @GetMapping
    public String getCounselingList(Model model, @RequestParam(defaultValue = "1") int page) {
        Page<Counseling> counselingPage = counselingService.getCounselings(page);
        model.addAttribute("counselings", counselingPage.getContent());
        model.addAttribute("totalPages", counselingPage.getTotalPages());
        model.addAttribute("currentPage", page);
        return "counseling";
    }

    // 상담 등록 및 수정 처리
    @PostMapping("/save")
    public String saveCounseling(Counseling counseling) {
        counselingService.saveCounseling(counseling);
        return "redirect:/center/counseling";
    }

    // 상담 삭제
    @DeleteMapping("/delete/{id}")
    @ResponseBody
    public ResponseEntity<Void> deleteCounseling(@PathVariable Long id) {
        counselingService.deleteCounseling(id);
        return ResponseEntity.ok().build();
    }

    // 상담 수정 시 데이터 가져오기
    @GetMapping("/{id}")
    @ResponseBody
    public Counseling getCounseling(@PathVariable Long id) {
        return counselingService.getCounseling(id);
    }

}
