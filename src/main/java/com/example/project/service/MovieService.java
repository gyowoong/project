package com.example.project.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.example.project.dto.GenreDto;
import com.example.project.dto.MovieDto;
import com.example.project.dto.MoviePeopleDto;
import com.example.project.dto.PageRequestDTO;
import com.example.project.dto.PageResultDTO;
import com.example.project.dto.PeopleDto;
import com.example.project.entity.Genre;
import com.example.project.entity.Movie;
import com.example.project.entity.MoviePeople;
import com.example.project.entity.People;

public interface MovieService {

        PageResultDTO<MovieDto, Movie> getList(PageRequestDTO requestDto);

        // MovieDto read(Long id);

        // List<String> getDirectorList(Long id);

        // List<String> getActorList(Long id);

        // List<String> getGenreList(Long id);

        List<MovieDto> getMovieListByPersonId(Long id);

        MovieDto getMovieDetail(Long id);

        public default MovieDto entityToDto(Movie movie, List<MoviePeople> moviePeoples, List<People> people,
                        List<Genre> genres) {
                // MovieDto 생성
                MovieDto movieDto = MovieDto.builder()
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

                // MoviePeopleDto 생성 (movieDto와 peopleId 설정)
                List<MoviePeopleDto> moviePeopleDtos = moviePeoples.stream().map(moviePeople -> {
                        return MoviePeopleDto.builder()
                                        .id(moviePeople.getId())
                                        .character(moviePeople.getCharacter())
                                        .role(moviePeople.getRole())
                                        .movieId(movie.getId()) // movieId 설정
                                        .peopleId(moviePeople.getPeople().getId()) // peopleId 설정
                                        .build();
                }).collect(Collectors.toList());

                // PeopleDto 생성 (moviePeopleDtos 포함)
                List<PeopleDto> peopleDtos = people.stream().map(person -> {
                        return PeopleDto.builder()
                                        .id(person.getId())
                                        .gender(person.getGender())
                                        .job(person.getJob())
                                        .name(person.getName())
                                        .popularity(person.getPopularity())
                                        .profilePath(person.getProfilePath())
                                        .moviePeople(moviePeopleDtos.stream()
                                                        .filter(moviePeopleDto -> moviePeopleDto.getPeopleId()
                                                                        .equals(person.getId())) // 해당 peopleId에 맞는
                                                                                                 // moviePeopleDto만 필터링
                                                        .collect(Collectors.toList())) // 필터링된 moviePeopleDtos 설정
                                        .build();
                }).collect(Collectors.toList());

                // GenreDto 생성
                List<GenreDto> genreDtos = genres.stream().map(genre -> {
                        return GenreDto.builder()
                                        .id(genre.getId())
                                        .name(genre.getName())
                                        .build();
                }).collect(Collectors.toList());

                // movieDto에 관련된 정보 설정
                movieDto.setPeopleDtos(peopleDtos);
                movieDto.setGenresDtos(genreDtos);
                movieDto.setMoviePeopleDtos(moviePeopleDtos);

                return movieDto;
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