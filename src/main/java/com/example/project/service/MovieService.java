package com.example.project.service;

import java.util.List;

import com.example.project.dto.GenreDto;
import com.example.project.dto.MovieDto;
import com.example.project.dto.PageRequestDTO;
import com.example.project.dto.PageResultDTO;
import com.example.project.entity.Genre;
import com.example.project.entity.Movie;

public interface MovieService {

    PageResultDTO<MovieDto, Movie> getList(PageRequestDTO requestDto);

    MovieDto read(Long id);

    public default MovieDto entityToDto(Movie movie) {
        return MovieDto.builder()
                .id(movie.getId())
                .backdrop_path(movie.getBackdrop_path())
                .budget(movie.getBudget())
                .homepage(movie.getHomepage())
                .originalLanguage(movie.getOriginalLanguage())
                .originalTitle(movie.getOriginalTitle())
                .overview(movie.getOverview())
                .popularity(movie.getPopularity())
                .posterPath(movie.getPosterPath())
                .releaseDate(movie.getReleaseDate())
                .revenue(movie.getRevenue())
                .runtime(movie.getRuntime())
                .status(movie.getStatus())
                .tagline(movie.getTagline())
                .title(movie.getTitle())
                .voteAverage(movie.getVoteAverage())
                .voteCount(movie.getVoteCount())
                .build();
    }

    public default Movie dtoToEntity(MovieDto movieDto) {
        return Movie.builder()
                .id(movieDto.getId())
                .backdrop_path(movieDto.getBackdrop_path())
                .budget(movieDto.getBudget())
                .homepage(movieDto.getHomepage())
                .originalLanguage(movieDto.getOriginalLanguage())
                .originalTitle(movieDto.getOriginalTitle())
                .overview(movieDto.getOverview())
                .popularity(movieDto.getPopularity())
                .posterPath(movieDto.getPosterPath())
                .releaseDate(movieDto.getReleaseDate())
                .revenue(movieDto.getRevenue())
                .runtime(movieDto.getRuntime())
                .status(movieDto.getStatus())
                .tagline(movieDto.getTagline())
                .title(movieDto.getTitle())
                .voteAverage(movieDto.getVoteAverage())
                .voteCount(movieDto.getVoteCount())
                .build();
    }

}
