package com.example.project.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.project.dto.MovieDto;
import com.example.project.dto.PageRequestDTO;
import com.example.project.dto.PageResultDTO;
import com.example.project.dto.PeopleDto;
import com.example.project.entity.Movie;
import com.example.project.service.MovieService;
import com.example.project.service.PeopleService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RequestMapping("/rest")
@RequiredArgsConstructor
@Log4j2
@RestController
public class MovieRestController {

    private final MovieService movieService;
    private final PeopleService peopleService;

    // @GetMapping("/movieList")
    // public ResponseEntity<PageResultDTO<MovieDto, Movie>> getList(
    // @ModelAttribute("requestDto") @RequestBody PageRequestDTO requestDto) {
    // log.info("rest 영화 전체 목록 요청 {}", requestDto);
    // PageResultDTO<MovieDto, Movie> result = movieService.getList(requestDto);
    // return new ResponseEntity<>(result, HttpStatus.OK);
    // }

    @GetMapping("/movieDetail/{id}")
    public ResponseEntity<MovieDto> getMovieDetail(@PathVariable Long id,
            @ModelAttribute("requestDto") @RequestBody PageRequestDTO requestDto) {
        log.info("rest 영화 전체 목록 요청 {}", requestDto);
        MovieDto movieDto = movieService.getMovieDetail(id);
        return new ResponseEntity<>(movieDto, HttpStatus.OK);
    }

}
