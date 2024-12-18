package com.example.project.service;

import java.util.List;

import com.example.project.dto.MovieDto;

public interface MyPageService {

    // 예매 정보를 기반으로 영화 목록 조회
    List<MovieDto> getBookedMovies(Long memberId);
}