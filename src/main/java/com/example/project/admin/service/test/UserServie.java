package com.example.project.admin.service.test;

import java.util.List;

import com.example.project.admin.Entity.MovieAdd;
import com.example.project.dto.MovieDetailsDTO;
import com.example.project.dto.MovieDto;
import com.example.project.dto.PageRequestDTO;
import com.example.project.dto.PageResultDTO;
import com.example.project.admin.dto.test.MovieAddDto;
import com.example.project.admin.dto.test.UserDto;
import com.example.project.entity.Genre;
import com.example.project.entity.Movie;
import com.example.project.entity.test.UserEntity;

public interface UserServie {


    List<UserEntity> allList(UserDto userDto);
    // public List<MovieDto> getMovieDetailsWithGenres(Long id);
    List<MovieDetailsDTO> getMovieDetails();
    // Long join(MovieDto movieDto);

    List<MovieAddDto> addList();

    List<MovieAddDto> selectList(String state,String name);

    List<String> getAllStates();

    default UserEntity dtoToEntity(UserDto dto){
        return UserEntity.builder()
        .userid(dto.getUserid())
        .password(dto.getPassword())
        .name(dto.getName())
        .addr(dto.getAddr())
        .telNo(dto.getTelNo())
        .email(dto.getEmail())
        .point(dto.getPoint())
        .reser(dto.isReser())
        .gender(dto.getGender())
        .age(dto.getAge())
        .brith(dto.getBrith())
        .build();
    }
    default UserDto entityToDto(UserEntity entity){
        return UserDto.builder()
        .userid(entity.getUserid())
        .password(entity.getPassword())
        .name(entity.getName())
        .addr(entity.getAddr())
        .telNo(entity.getTelNo())
        .email(entity.getEmail())
        .point(entity.getPoint())
        .reser(entity.isReser())
        .gender(entity.getGender())
        .age(entity.getAge())
        .brith(entity.getBrith())
        .build();
    }


    default Movie mDtoToEntity(MovieDto dto){
        return Movie.builder()
        .id(dto.getId())
        .backdrop_path(dto.getBackdrop_path())
        .budget(dto.getBudget())
        .homepage(dto.getHomepage())
        .originalLanguage(dto.getOriginalLanguage())
        .originalTitle(dto.getOriginalTitle())
        .overview(dto.getOverview())
        .popularity(dto.getPopularity())
        .posterPath(dto.getPosterPath())
        .releaseDate(dto.getReleaseDate())
        .runtime(dto.getRuntime())
        .status(dto.getStatus())
        .tagline(dto.getTagline())
        .title(dto.getTitle())
        .voteAverage(dto.getVoteAverage())
        .voteCount(dto.getVoteCount())
        .movieGenres(dto.getMovieGenres())
        .moviePeople(dto.getMoviePeople())
        .build();
    }
    default MovieDto mEntityToDto(Movie movie, List<Genre> genre, String releaseDate){
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
        .runtime(movie.getRuntime())
        .status(movie.getStatus())
        .tagline(movie.getTagline())
        .title(movie.getTitle())
        .voteAverage(movie.getVoteAverage())
        .voteCount(movie.getVoteCount())
        .movieGenres(movie.getMovieGenres())
        .moviePeople(movie.getMoviePeople())
        .build();
    }
}
