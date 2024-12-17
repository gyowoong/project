package com.example.project.service;

import java.util.List;

import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

import com.example.project.dto.GenreDto;
import com.example.project.entity.Genre;
import com.example.project.repository.GenreRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@RequiredArgsConstructor
@Service
public class GenreServiceImpl implements GenreService {

    private final GenreRepository genreRepository;

    @Override
    public List<GenreDto> getGenres() {
        List<Genre> result = genreRepository.findAll();
        return result.stream().map(entity -> entityToDto(entity)).collect(Collectors.toList());
    }

}
