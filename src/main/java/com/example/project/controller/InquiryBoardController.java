package com.example.project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/inquiries")
public class InquiryBoardController {

    @GetMapping("/center/emailInquiryPage")
    public String showEmailInquiryPage(Model model) {
        return "email";
    }

}
