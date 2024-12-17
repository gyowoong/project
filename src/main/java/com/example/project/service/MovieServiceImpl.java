package com.example.project.service;

import java.util.List;
import java.util.function.Function;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.project.dto.GenreDto;
import com.example.project.dto.MovieDto;
import com.example.project.dto.PageRequestDTO;
import com.example.project.dto.PageResultDTO;
import com.example.project.entity.Genre;
import com.example.project.entity.Movie;
import com.example.project.repository.GenreRepository;
import com.example.project.repository.MovieRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@RequiredArgsConstructor
@Service
public class MovieServiceImpl implements MovieService {

    private final MovieRepository movieRepository;

    @Override
    public PageResultDTO getList(PageRequestDTO requestDto) {
        // 페이지 나누기 개념 추가
        Pageable pageable = requestDto.getPageable(Sort.by(requestDto.getSort()).descending());
        Page<Movie> movies = movieRepository.getTotalList(requestDto.getType(), requestDto.getKeyword(),
                requestDto.getMovieList(),
                requestDto.getGenre(), pageable);
        Function<Movie, MovieDto> function = (en -> entityToDto(en));

        return new PageResultDTO<>(movies, function);
    }

}
