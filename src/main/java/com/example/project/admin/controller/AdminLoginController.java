package com.example.project.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.log4j.Log4j2;

@Controller
@Log4j2
@RequestMapping("/admin")
public class AdminLoginController {
     @GetMapping("/login")
    public void getHome() {
        log.info("home 폼 요청");
        
    }
}
