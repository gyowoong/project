package com.example.project.controller;

<<<<<<< HEAD
import org.springframework.stereotype.Controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


=======
import lombok.extern.log4j.Log4j2;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
>>>>>>> origin/main

@Log4j2
@Controller
public class HomeController {
<<<<<<< HEAD
    
    @GetMapping("/")
    public String getMethodName() {
        return "redirect:/index";
    }
    
=======

    @GetMapping("/")
    public String getHome() {
        log.info("home 폼 요청");
        return "/index";
    }

>>>>>>> origin/main
}
