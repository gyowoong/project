package com.example.project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class InquiryBoardController {

    @GetMapping("/center/emailInquiryPage")
    public String showEmailInquiryPage(Model model) {
        return "email";
    }
}
