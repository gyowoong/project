package com.example.project.repository.reserve;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.project.entity.reserve.Screening;

public interface ScreeningRepository extends JpaRepository<Screening, Long> {

    // 극장에 상영중인 영화
    @Query("SELECT DISTINCT s.movieTitle FROM Screening s WHERE s.auditorium.theater.theaterId = :theaterId")
    List<String> findMoviesByTheaterId(@Param("theaterId") Long theaterId);

    // 영화별 상영시간표
    @Query("SELECT s FROM Screening s WHERE s.movieTitle = :movieTitle")
    List<Screening> findScreeningsByMovieTitle(@Param("movieTitle") String movieTitle);

}
