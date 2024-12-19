package com.example.project.admin.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.project.admin.Entity.MovieAdd;
import com.example.project.admin.dto.test.MovieAddDto;
import com.example.project.admin.dto.test.UserDto;
import com.example.project.admin.service.test.UserServie;
import com.example.project.dto.MovieDetailsDTO;
import com.example.project.entity.test.UserEntity;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RequiredArgsConstructor
@Controller
@Log4j2
@RequestMapping("/admin/page")
public class AdminController {

    private final UserServie userServie;

    @GetMapping("/index")
    public void getHome() {
        log.info("home 폼 요청");

    }

    @GetMapping("/user")
    public void getUser(UserDto userDto, Model model) {
        log.info("home 폼 요청");
        List<UserEntity> list = userServie.allList(userDto);
        model.addAttribute("list", list);

    }

    @GetMapping("/create")
<<<<<<< HEAD
    public void getCreate(Model model) {
        log.info("create 폼 요청");
         // 서비스 호출
         List<MovieDetailsDTO> movieDetails = userServie.getMovieDetails();
=======
    public void getCreate(MovieDto movieDto, Model model) {
>>>>>>> main

         // 모델에 데이터 추가
         model.addAttribute("movieDetails", movieDetails);
    }

    @GetMapping("/join")
    public void getJoin() {
        log.info("join 폼 요청");
        
    }
    @GetMapping("/movie")
    public void getMovie(String state,String name,Model model) {
        log.info("movie 폼 요청 {} {}" ,state ,name);
        List<MovieAddDto> add = userServie.selectList(state,name);
        model.addAttribute("add", add);
        model.addAttribute("states", userServie.getAllStates());
        model.addAttribute("state", state);
        model.addAttribute("name", name);
        log.info("movie 폼 요청 {}" ,model);
        
    }
}
