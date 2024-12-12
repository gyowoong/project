package com.example.project.controller;

import lombok.extern.log4j.Log4j2;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Log4j2
@Controller
@RequestMapping("/movie")
public class MovieController {

    @GetMapping("/main")
    public void getHome() {
        log.info("home 폼 요청");

    }
    @GetMapping("/read")
    public void getRead() {
        log.info("home 폼 요청");

    }

    @GetMapping("/reservation")
    public void getReservation() {
        log.info("home 폼 요청");

    }

    @GetMapping("/center")
    public void getCenter() {
        log.info("home 폼 요청");

    }

    @GetMapping("/movieDetail")
    public void getMovieDetail(@RequestParam Long id, Long page, Model model) {
        log.info("movieDetail 폼 요청 {}", id);
        model.addAttribute("id", id);
        model.addAttribute("page", page);
    }

    @GetMapping({ "/now_playing", "/upcoming", "/popular" })
    public void getMovieList(Long genre, Long page, String movieList, Model model) {
        log.info("movieList 폼 요청 {}", page);
        model.addAttribute("genre", genre);
        model.addAttribute("page", page);
        model.addAttribute("movieList", movieList);
    }

}
