package com.example.project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.log4j.Log4j2;

@Controller
@Log4j2
@RequestMapping("/admin/page")
public class adminController {
    @GetMapping("/index")
    public void getHome() {
        log.info("home 폼 요청");
        
    }
    @GetMapping("/user")
    public void getUser() {
        log.info("home 폼 요청");
        
    }
    @GetMapping("/create")
    public void getCreate() {
        log.info("home 폼 요청");
        
    }
}
