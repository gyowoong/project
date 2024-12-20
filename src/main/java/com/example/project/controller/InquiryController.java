package com.example.project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.project.entity.Inquiry;
import com.example.project.service.InquiryService;

@Controller
public class InquiryController {

    private final InquiryService inquiryService;

    public InquiryController(InquiryService inquiryService) {
        this.inquiryService = inquiryService;
    }

    @PostMapping("/center/email/save")
    public String saveInquiry(@RequestParam String name,
            @RequestParam String email,
            @RequestParam String content) {
        Inquiry inquiry = new Inquiry();
        inquiry.setName(name);
        inquiry.setEmail(email);
        inquiry.setContent(content);

        inquiryService.save(inquiry); // 저장 로직
        return "redirect:/center/email"; // 저장 후 게시판으로 리다이렉트
    }

}
