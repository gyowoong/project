package com.example.project.admin.controller;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.project.dto.MovieDto;
import com.example.project.dto.test.UserDto;
import com.example.project.entity.Movie;
import com.example.project.entity.test.UserEntity;
import com.example.project.service.test.UserServie;

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
    public void getCreate(MovieDto movieDto, Model model ) {

        log.info("home 폼 요청");
        List<Movie> list = userServie.mList(movieDto);
        model.addAttribute("movie", list);
    }
}
