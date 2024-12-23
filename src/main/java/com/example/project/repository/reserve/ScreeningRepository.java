package com.example.project.repository.reserve;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.project.entity.Movie;
import com.example.project.entity.Screening;

public interface ScreeningRepository extends JpaRepository<Screening, Long> {

    @Query("SELECT DISTINCT s.movie FROM Screening s WHERE s.theater.theaterId = :theaterId")
    List<Movie> findMoviesByTheaterId(@Param("theaterId") Long theaterId);

    @Query("SELECT s FROM Screening s WHERE s.theater.theaterId = :theaterId AND s.movie.Id = :movieId")
    List<Screening> findScreenings(@Param("theaterId") Long theaterId, @Param("movieId") Long movieId);
}
