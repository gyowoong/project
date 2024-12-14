package com.example.project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.project.entity.Movie;

public interface MovieRepository extends JpaRepository<Movie, Long> {

    @Query("SELECT g.name FROM Movie m JOIN MovieGenre mg ON m.id = mg.movie.id JOIN Genre g ON mg.genre.id = g.id WHERE m.id = :id")
    List<String> getGenre(Long id);

    @Query("SELECT p.name FROM Movie m JOIN MoviePeople mp ON m.id = mp.movie.id JOIN People p ON mp.people.id = p.id WHERE m.id = :id")
    List<String> getpeople(Long id);

}
