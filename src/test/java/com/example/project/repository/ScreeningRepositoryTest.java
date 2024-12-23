package com.example.project.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.example.project.entity.Auditorium;
import com.example.project.entity.Movie;
import com.example.project.entity.Screening;
import com.example.project.entity.Theater;
import com.example.project.repository.movie.MovieRepository;
import com.example.project.repository.reserve.ScreeningRepository;
import com.example.project.repository.reserve.TheaterRepository;

@SpringBootTest
@Transactional
public class ScreeningRepositoryTest {

    @Autowired
    private ScreeningRepository screeningRepository;

    @Autowired
    private TheaterRepository theaterRepository;

    @Autowired
    private MovieRepository movieRepository;

}
