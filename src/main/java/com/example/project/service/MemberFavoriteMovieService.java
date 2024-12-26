package com.example.project.service;

import java.util.List;

import com.example.project.entity.Movie;

public interface MemberFavoriteMovieService {
    void addFavoriteMovie(Long memberId, Long movieId);

    List<Movie> getFavoriteMoviesByMemberId(Long memberId);

    boolean existsByMemberIdAndMovieId(Long memberId, Long movieId);

    void deleteFavoriteMovie(Long memberId, Long movieId);
}
