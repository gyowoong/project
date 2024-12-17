package com.example.project.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.project.entity.Movie;

public interface MovieRepository extends JpaRepository<Movie, Long> {

    Page<Movie> getTotalList(String type, String keyword, String movieList, Long genreId, Pageable pageable);

}
