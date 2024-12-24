package com.example.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.project.entity.Counseling;
import com.example.project.entity.EmailInquiry;
import com.example.project.service.CounselingService;
import com.example.project.service.EmailService;
import com.example.project.service.InquiryService;

@Controller
@RequestMapping("/center/email")
public class EmailController {

    @Autowired
    private EmailService emailService;

    @Autowired
    private CounselingService counselingService;

    private final InquiryService inquiryService;

    @Autowired
    public EmailController(InquiryService inquiryService) {
        this.inquiryService = inquiryService;
    }

    @PostMapping("/delete")
    public String deleteInquiry(@RequestParam Long id, RedirectAttributes redirectAttributes) {
        try {
            inquiryService.delete(id);
            redirectAttributes.addFlashAttribute("message", "삭제가 완료되었습니다.");
        } catch (IllegalArgumentException e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
        }
        return "redirect:/center/email";
    }

    @PostMapping("/center/email/save")
    public String saveEmail(@ModelAttribute EmailInquiry emailInquiry) {
        // 이메일 저장
        emailService.save(emailInquiry);

        // 상담 내역으로 변환
        Counseling counseling = new Counseling();
        counseling.setContent(emailInquiry.getContent());
        counseling.setCounselingType("이메일 상담");
        counseling.setStatus("대기");

        // 상담 내역 저장
        counselingService.save(counseling);

        // 상담 내역 페이지로 리디렉션
        return "redirect:/counseling";
    }
}
