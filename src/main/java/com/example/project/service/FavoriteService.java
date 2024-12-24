package com.example.project.service;

import java.util.List;

import com.example.project.dto.MovieDto;
import com.example.project.entity.Movie;

public interface FavoriteService {
    void addFavorite(String memberId, Long movieId);

    List<MovieDto> getFavorites(String memberId);
}