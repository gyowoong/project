package com.example.project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Controller
@RequestMapping("/center")
public class CenterController {

    @GetMapping("")
    public String getCenter() {
        log.info("center main 폼 요청");
        return "/center/home";
    }

    @GetMapping("/chat")
    public void getMethodName() {
        log.info(" ai 챗봇 요청");
    }

}
