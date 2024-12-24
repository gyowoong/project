package com.example.project.controller;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.project.service.ReviewService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/review")
public class ReviewController {

    private final ReviewService reviewService;

    @PostMapping("/submit")
    public String submitReview(@RequestParam Long movieId, @RequestParam String content, Principal principal) {
        // 인증된 사용자 정보 가져오기
        String memberId = principal.getName();

        // 리뷰 저장
        reviewService.saveReview(memberId, movieId, content);

        // 리뷰 작성 후 해당 영화 페이지로 리다이렉트
        return "redirect:/movie/details/" + movieId;
    }
}