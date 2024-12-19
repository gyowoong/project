package com.example.project.repository.movie;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.project.entity.Movie;
import com.example.project.entity.People;

public interface MovieCustomRepository {
    Page<Movie> getTotalList(String type, String keyword, String movieList, Long genreId, Pageable pageable);

}
