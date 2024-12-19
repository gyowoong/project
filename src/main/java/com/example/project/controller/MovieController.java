package com.example.project.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import org.springframework.web.bind.annotation.RequestMapping;

import com.example.project.dto.GenreDto;
import com.example.project.dto.MovieDto;
import com.example.project.dto.PageRequestDTO;
import com.example.project.dto.PageResultDTO;

import com.example.project.entity.Movie;
import com.example.project.service.GenreService;
import com.example.project.service.MovieService;

@RequiredArgsConstructor
@Log4j2
@Controller
@RequestMapping("/movie")
public class MovieController {

    private final MovieService movieService;
    private final GenreService genreService;

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

    @GetMapping("/movieList")
    public void getMovieList(@ModelAttribute("requestDto") PageRequestDTO requestDto,
            Model model) {
        log.info("도서 전체 목록 요청 {}", requestDto);
        PageResultDTO<MovieDto, Movie> result = movieService.getList(requestDto);
        List<GenreDto> genreDtos = genreService.getGenres();

        model.addAttribute("result", result);
        model.addAttribute("genreDtos", genreDtos);
    }

    @GetMapping("/movieDetail")
    public void getMovieDetail(Long id, String movieList, Long genre, String type, String keyword, Long page,
            Model model) {
        log.info("movieDetail 폼 요청 {}", id);
        model.addAttribute("id", id);
        model.addAttribute("movieList", movieList);
        model.addAttribute("genre", genre);
        model.addAttribute("type", type);
        model.addAttribute("keyword", keyword);
        model.addAttribute("page", page);
    }

}
