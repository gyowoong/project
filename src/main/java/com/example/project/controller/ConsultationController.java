package com.example.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.project.entity.Consultation;
import com.example.project.service.ConsultationAnswerService;
import com.example.project.service.ConsultationService;

import java.util.List;

@Controller
@RequestMapping("/consultations")
public class ConsultationController {

    private final ConsultationService consultationService;
    private final ConsultationAnswerService consultationAnswerService;

    @Autowired
    public ConsultationController(ConsultationService consultationService,
            ConsultationAnswerService consultationAnswerService) {
        this.consultationService = consultationService;
        this.consultationAnswerService = consultationAnswerService;
    }

    // 상담 목록 조회 (웹 페이지에 표시용)
    @GetMapping
    public String getConsultations(Model model) {
        List<Consultation> consultations = consultationService.getAllConsultations();
        model.addAttribute("consultations", consultations);
        return "consultation"; // consultation.html로 데이터 전달
    }

    // 상담에 답변 추가
    @PostMapping("/{consultationId}/answer")
    public String addAnswer(@PathVariable Long consultationId, String answerContent) {
        consultationAnswerService.addAnswer(consultationId, answerContent);
        return "redirect:/consultations"; // 답변 추가 후 상담 목록 페이지로 리다이렉트
    }
}
