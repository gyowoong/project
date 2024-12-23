package com.example.project.repository.reserve;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.project.entity.Movie;
import com.example.project.entity.reserve.Screening;
import com.example.project.entity.reserve.Theater;

public interface ScreeningRepository extends JpaRepository<Screening, Long> {

    @Query("SELECT DISTINCT s.movie FROM Screening s WHERE s.auditorium.theater.theaterId = :theaterId")
    List<Movie> findMoviesByTheaterId(@Param("theaterId") Long theaterId);

    // @Query("SELECT s FROM Screening s WHERE s.auditorium.theater.theaterId =
    // :theaterId AND s.movie.movieId = :movieId")
    // List<Screening> findScreenings(@Param("theaterId") Long theaterId,
    // @Param("movieId") Long movieId);

    @Query("SELECT s FROM Screening s JOIN s.auditorium a WHERE a.theater.theaterId = :theaterId")
    List<Screening> findByTheaterId(@Param("theaterId") Long theaterId);

    @Query("SELECT a.theater FROM Screening s JOIN s.auditorium a WHERE s.screeningId = :screeningId")
    Theater findTheaterByScreeningId(@Param("screeningId") Long screeningId);
}
