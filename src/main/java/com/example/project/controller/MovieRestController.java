package com.example.project.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.project.dto.MovieDto;
import com.example.project.dto.PageRequestDTO;
import com.example.project.dto.PageResultDTO;
import com.example.project.entity.Movie;
import com.example.project.service.MovieService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RequestMapping("/movies")
@RequiredArgsConstructor
@Log4j2
@RestController
public class MovieRestController {

    private final MovieService movieService;

    @GetMapping("/movieList")
    public ResponseEntity<PageResultDTO<MovieDto, Movie>> getList(
            @ModelAttribute("requestDto") PageRequestDTO requestDto) {
        log.info("rest 도서 전체 목록 요청 {}", requestDto);
        PageResultDTO<MovieDto, Movie> result = movieService.getList(requestDto);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

}
