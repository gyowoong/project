package com.example.project.service;

import java.util.List;

import com.example.project.entity.Movie;

public interface MemberFavoriteMovieService {
    void addFavoriteMovie(Long mid, Long movieId);

    List<Movie> getFavoriteMoviesByMemberId(Long mid);
}
