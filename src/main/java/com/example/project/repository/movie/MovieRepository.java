package com.example.project.repository.movie;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.project.entity.Genre;
import com.example.project.entity.Movie;

public interface MovieRepository extends JpaRepository<Movie, Long>, MovieCustomRepository {

    @Query("SELECT m FROM Movie m JOIN m.movieGenres mg JOIN mg.genre g WHERE g IN :genres")
    List<Movie> findMoviesByGenres(List<Genre> genres);

    @Query("SELECT m FROM Movie m JOIN m.moviePeople mp JOIN mp.person p WHERE p.id = :directorId AND p.job = 'Directing'")
    List<Movie> findMoviesByDirectorId(Long directorId);

}
