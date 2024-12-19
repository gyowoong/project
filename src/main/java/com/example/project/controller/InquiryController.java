package com.example.project.controller;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.project.entity.Inquiry;
import com.example.project.service.InquiryService;

import jakarta.validation.Valid;

@Controller
public class InquiryController {
    private final InquiryService inquiryService;

    public InquiryController(InquiryService inquiryService) {
        this.inquiryService = inquiryService;
    }

    @GetMapping("/inquiries")
    public String listInquiries(Model model) {
        // 모든 문의 데이터를 조회
        List<Inquiry> inquiries = inquiryService.findAll();
        model.addAttribute("inquiries", inquiries);
        model.addAttribute("inquiry", new Inquiry());
        return "email"; // email.html 렌더링
    }

    @PostMapping("/inquiries")
    public String saveInquiry(
            @Valid @ModelAttribute Inquiry inquiry,
            BindingResult bindingResult,
            Model model) {

        if (bindingResult.hasErrors()) {
            // 유효성 검사 오류가 있을 경우, 폼으로 다시 이동
            model.addAttribute("inquiries", inquiryService.findAll());
            return "center/email"; // email.html로 다시 이동
        }

        // 저장 로직
        inquiryService.save(inquiry);
        return "redirect:/inquiries"; // 저장 후 목록 페이지로 리다이렉트
    }

    @GetMapping
    public String listInquiries(@RequestParam(defaultValue = "1") int page, Model model) {
        Page<Inquiry> inquiries = inquiryService.getInquiries(page);
        model.addAttribute("inquiries", inquiries.getContent());
        model.addAttribute("totalPages", inquiries.getTotalPages());
        model.addAttribute("currentPage", page);
        return "center/email";
    }

    @GetMapping("/{id}")
    @ResponseBody
    public Inquiry getInquiry(@PathVariable Long id) {
        return inquiryService.getInquiryById(id);
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    public void deleteInquiry(@PathVariable Long id) {
        inquiryService.deleteInquiry(id);
    }

}
