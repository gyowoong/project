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

import jakarta.servlet.http.HttpSession;
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
    public String loginRedirect(HttpSession session, Model model) {
        String memberId = (String) session.getAttribute("memberId");

        if (memberId != null) {
            // 이미 로그인된 상태라면 마이페이지로 리다이렉트
            return "redirect:/member/mypage";
        }

        // 로그인이 안 되어 있다면 로그인 페이지로 이동
        model.addAttribute("memberDto", new MemberDto()); // 빈 객체 전달
        return "member/login";
    }

    @GetMapping("/register")
    public String showRegisterPage(Model model) {
        model.addAttribute("memberDto", new MemberDto());
        return "member/register"; // Thymeleaf 템플릿 경로
    }

    @PostMapping("/register")
    public String register(@Valid @ModelAttribute("memberDto") MemberDto memberDto,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes,
            Model model) {

        // 입력값 유효성 검사
        if (bindingResult.hasErrors()) {
            return "member/register";
        }

        // 아이디 중복 검사
        if (memberService.isMemberIdDuplicate(memberDto.getMemberId())) {
            model.addAttribute("idError", "이미 사용 중인 아이디입니다.");
            return "member/register";
        }

        // 이메일 중복 검사
        if (memberService.isEmailDuplicate(memberDto.getEmail())) {
            model.addAttribute("emailError", "이미 사용 중인 이메일입니다.");
            return "member/register";
        }

        // 회원가입 처리
        memberService.registerMember(memberDto);
        redirectAttributes.addFlashAttribute("success", "회원가입이 완료되었습니다.");
        return "redirect:/member/login";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute("memberDto") MemberDto memberDto, HttpSession session, Model model) {
        boolean isValidUser = memberService.validateMember(memberDto.getMemberId(), memberDto.getPassword());

        if (!isValidUser) {
            model.addAttribute("error", "아이디 또는 비밀번호가 잘못되었습니다.");
            return "member/login";
        }
        session.setAttribute("memberId", memberDto.getMemberId());
        return "redirect:/"; // 로그인 성공 시 메인 페이지로 이동
    }

    @GetMapping("/mypage")
    public String getMypage(HttpSession session, Model model) {
        // 세션에서 memberId 가져오기
        String memberId = (String) session.getAttribute("memberId");

        if (memberId == null) {
            return "redirect:/login"; // 로그인되지 않은 경우 로그인 페이지로 리다이렉트
        }

        // 회원 정보 조회
        MemberDto memberDto = memberService.getMemberById(memberId);
        model.addAttribute("member", memberDto);

        return "member/mypage"; // mypage.html 반환
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        // 세션 무효화 (로그아웃 처리)
        session.invalidate();
        return "member/login"; // 로그아웃 후 로그인 페이지로 리다이렉트
    }

    // 비밀번호 확인 페이지 렌더링
    @GetMapping("/verify-password")
    public String verifyPasswordForm(HttpSession session, Model model) {
        String memberId = (String) session.getAttribute("memberId");

        if (memberId == null) {
            return "redirect:/member/login"; // 로그인 안 되어 있으면 로그인 페이지로
        }

        model.addAttribute("memberId", memberId);
        return "member/verify-password"; // 비밀번호 확인 페이지
    }

    // 비밀번호 검증
    @PostMapping("/verify-password")
    public String verifyPassword(HttpSession session, String password, Model model) {
        String memberId = (String) session.getAttribute("memberId");

        if (memberId == null) {
            return "redirect:/member/login"; // 로그인 안 되어 있으면 로그인 페이지로
        }

        boolean isValid = memberService.verifyPassword(memberId, password);

        if (!isValid) {
            model.addAttribute("error", "비밀번호가 잘못되었습니다.");
            return "member/verify-password";
        }

        return "redirect:/member/edit"; // 비밀번호가 올바르면 수정 페이지로
    }

    @GetMapping("/edit")
    public String editMemberForm(HttpSession session, Model model) {
        String memberId = (String) session.getAttribute("memberId");

        if (memberId == null) {
            return "redirect:/member/login"; // 로그인 안 되어 있으면 로그인 페이지로
        }

        MemberDto memberDto = memberService.getMemberById(memberId);
        model.addAttribute("member", memberDto);
        return "member/edit"; // 수정 페이지
    }

    @PostMapping("/edit")
    public String editMember(@ModelAttribute("member") MemberDto memberDto, HttpSession session, Model model) {
        // 세션에서 현재 로그인된 사용자 확인
        String memberId = (String) session.getAttribute("memberId");
        if (memberId == null) {
            return "redirect:/member/login"; // 로그인하지 않은 경우
        }

        // DTO에 세션에서 가져온 memberId 설정
        memberDto.setMemberId(memberId);

        // 서비스 호출하여 회원정보 업데이트
        try {
            memberService.updateMember(memberDto);
        } catch (RuntimeException e) {
            model.addAttribute("error", e.getMessage());
            return "member/edit";
        }

        return "redirect:/member/mypage"; // 성공 시 마이페이지로 리다이렉트
    }
}