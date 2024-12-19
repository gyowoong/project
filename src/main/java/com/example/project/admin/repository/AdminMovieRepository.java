package com.example.project.admin.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.project.entity.Movie;

public interface AdminMovieRepository extends JpaRepository<Movie, Long> {

//     @Query("SELECT m, g, mg FROM MovieGenre mg " +
//        "JOIN mg.genre g " +
//        "JOIN mg.movie m " +
//        "WHERE m.id = :id")
// List<Object[]> getMovieDetailsWithGenres(Long id);

@Query(value = """
            SELECT 
                m.TITLE AS movie_title,
                LISTAGG(g.name, ', ') WITHIN GROUP (ORDER BY g.name) AS genres,
                m.RELEASE_DATE AS release_date
            FROM 
                Movie m
            JOIN 
                MOVIE_GENRE mg ON m.ID = mg.MOVIE_ID
            JOIN 
                Genre g ON g.ID = mg.GENRE_ID
            GROUP BY 
                m.TITLE, m.RELEASE_DATE, m.ID
            ORDER BY 
                m.RELEASE_DATE DESC
            """, nativeQuery = true)
List<Object[]> getMovieDetails();




}
