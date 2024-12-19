package com.example.project.repository.movie;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.project.entity.MoviePeople;

public interface MoviePeopleRepository extends JpaRepository<MoviePeople, Long> {

}
