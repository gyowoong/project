package com.example.project.controller;

import lombok.extern.log4j.Log4j2;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Log4j2
@Controller
public class HomeController {

    @GetMapping("/")
<<<<<<< HEAD
    public String getHome() {
        log.info("home 폼 요청");
        return "/index";
=======
    public String getIndex() {
        log.info("home 폼 요청");
        return "/movie/main";
>>>>>>> main
    }

}
