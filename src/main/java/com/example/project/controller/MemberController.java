package com.example.project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.project.dto.MemberDto;
import com.example.project.service.MemberService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Controller
@RequestMapping("/member")
@RequiredArgsConstructor // Lombok 자동 생성자 주입
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/login")
    public void getLogin() {
        log.info("로그인 폼 요청");
    }

    @GetMapping("/register")
    public String showRegisterPage(Model model) {
        model.addAttribute("memberDto", new MemberDto());
        return "member/register"; // Thymeleaf 템플릿 경로
    }

    @PostMapping("/register")
    public String registerMember(@Valid @ModelAttribute("memberDto") MemberDto memberDto,
            BindingResult result,
            RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "member/register";
        }

        try {
            memberService.registerMember(memberDto);
            redirectAttributes.addFlashAttribute("successMessage", "회원가입 성공!");
            return "redirect:/member/login";
        } catch (Exception e) {
            result.rejectValue("memberId", "error.memberDto", "중복된 아이디입니다.");
            return "member/register";
        }
    }

}