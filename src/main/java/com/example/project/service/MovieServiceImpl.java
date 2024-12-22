package com.example.project.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.project.dto.MovieDto;
import com.example.project.dto.PageRequestDTO;
import com.example.project.dto.PageResultDTO;
import com.example.project.entity.Genre;
import com.example.project.entity.Movie;
import com.example.project.entity.MoviePeople;
import com.example.project.entity.People;
import com.example.project.repository.movie.MovieRepository;

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
        Page<Object[]> movies = movieRepository.getTotalList(requestDto.getType(),
                requestDto.getKeyword(),
                requestDto.getMovieList(),
                requestDto.getGenre(), pageable);
        Function<Object[], MovieDto> function = (en -> entityToDto((Movie) en[0],
                (List<MoviePeople>) en[1],
                (List<People>) en[2],
                (List<Genre>) en[3]));

        return new PageResultDTO<>(movies, function);
        // return null;
    }

    @Override
    public MovieDto read(Long id) {
        return null;
        // return entityToDto(movieRepository.findById(id).get());
    }

    // @Override
    // public List<String> getDirectorList(Long id) {
    // return movieRepository.getDirectorList(id);
    // }

    // @Override
    // public List<String> getActorList(Long id) {
    // return movieRepository.getActorList(id);
    // }

    // @Override
    // public List<String> getGenreList(Long id) {
    // return movieRepository.getGenreList(id);
    // }

    @Override
    public List<MovieDto> getMovieListByPersonId(Long id) {
        List<MovieDto> movieDtos = new ArrayList<>();
        movieRepository.getMovieListByPersonId(id).stream().forEach(movie -> {
            // movieDtos.add(entityToDto(movie));
        });
        return movieDtos;
    }

    @Override
    public MovieDto getMovieDetail(Long id) {
        Object[] result = movieRepository.getMovieDetailById(id);
        return entityToDto((Movie) result[0],
                (List<MoviePeople>) result[1],
                (List<People>) result[2],
                (List<Genre>) result[3]);
    }

}
