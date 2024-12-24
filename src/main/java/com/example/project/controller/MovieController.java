package com.example.project.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.project.dto.GenreDto;
import com.example.project.dto.MovieDto;
import com.example.project.dto.MoviePeopleDto;
import com.example.project.dto.PageRequestDTO;
import com.example.project.dto.PageResultDTO;
import com.example.project.dto.PeopleDto;
import com.example.project.entity.Movie;
import com.example.project.entity.People;
import com.example.project.service.GenreService;
import com.example.project.service.MovieService;
import com.example.project.service.PeopleService;

@RequiredArgsConstructor
@Log4j2
@Controller
@RequestMapping("/movie")
public class MovieController {

    private final MovieService movieService;
    private final GenreService genreService;
    private final PeopleService peopleService;

    @GetMapping("/main")
    public void getHome() {
        log.info("home 폼 요청");

    }

    // @GetMapping("/read")
    // public void getRead() {
    // log.info("home 폼 요청");

    // }

    @GetMapping("/reservation")
    public void getReservation() {
        log.info("home 폼 요청");

    }

    @GetMapping("/movieList")
    public void getMovieList(@ModelAttribute("requestDto") PageRequestDTO requestDto,
            Model model) {
        log.info("영화 전체 목록 요청 {}", requestDto);
        if (requestDto.getKeyword() != null && requestDto.getKeyword() != "") {
            if (requestDto.getType().contains("m")) {
                PageResultDTO<MovieDto, Movie> movies = movieService.getList(requestDto);
                model.addAttribute("movies", movies);
                log.info("토탈 {}", movies.getTotalPage());
            }
            if (requestDto.getType().contains("p")) {

                PageResultDTO<PeopleDto, People> people = peopleService.getList(requestDto);
                log.info("토탈 {}", people.getTotalPage());
                model.addAttribute("people", people);

            }
        } else {
            PageResultDTO<MovieDto, Movie> movies = movieService.getList(requestDto);
            model.addAttribute("movies", movies);
        }

        List<GenreDto> genreDtos = genreService.getGenres();
        model.addAttribute("genreDtos", genreDtos);
    }

    @GetMapping("/movieDetail")
    public void getMovieDetail(Long id, @ModelAttribute("requestDto") PageRequestDTO requestDto,
            Model model) {
        log.info("movieDetail 폼 요청 {}", id);

        MovieDto movieDto = movieService.getMovieDetail(id);
        model.addAttribute("movieDto", movieDto);

        List<PeopleDto> directorList = new ArrayList<>();
        List<PeopleDto> actorList = new ArrayList<>();
        for (PeopleDto peopleDto : movieDto.getPeopleDtos()) {
            for (MoviePeopleDto moviePeopleDto : peopleDto.getMoviePeople()) {
                if (moviePeopleDto.getRole() != null && moviePeopleDto.getRole().equals("Director")) {
                    directorList.add(peopleDto);
                }
                if (moviePeopleDto.getRole() == null) {
                    actorList.add(peopleDto);
                }
            }
        }
        model.addAttribute("directorList", directorList);
        model.addAttribute("actorList", actorList);
    }

    @GetMapping("/personDetail")
    public void getPersonDetail(Long id, @ModelAttribute("requestDto") PageRequestDTO requestDto,
            Model model) {
        log.info("personDetail 폼 요청 {}", id);
        PeopleDto peopleDto = peopleService.read(id);
        List<MovieDto> movieDtos = movieService.getMovieListByPersonId(id);
        model.addAttribute("peopleDto", peopleDto);
        model.addAttribute("movieDtos", movieDtos);
    }
}