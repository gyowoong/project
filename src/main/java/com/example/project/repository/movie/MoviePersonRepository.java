package com.example.project.repository.movie;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.project.entity.MoviePerson;

public interface MoviePersonRepository extends JpaRepository<MoviePerson, Long> {
    boolean existsByMovieIdAndPersonId(Long movieId, Long personId);
}
