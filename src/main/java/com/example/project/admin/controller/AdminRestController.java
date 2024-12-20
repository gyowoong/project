package com.example.project.admin.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.project.admin.dto.test.MovieAddDto;
import com.example.project.admin.service.test.UserServie;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RequiredArgsConstructor
@RestController
@Log4j2
@RequestMapping("/admin/page")
public class AdminRestController {

    private final UserServie userServie;

    @DeleteMapping("/{tno}")
    public Long postRemove(@PathVariable Long tno) {
        log.info("영화관삭제 폼 요청 {} ", tno);
        userServie.delete(tno);

        return tno;
    }
}
