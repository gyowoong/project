package com.example.project.repository.reserve;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.project.entity.Theater;

public interface TheaterRepository extends JpaRepository<Theater, Long> {
    @Query("SELECT DISTINCT t.theaterState FROM Theater t")
    List<String> findAllRegions();

    @Query("SELECT t FROM Theater t WHERE t.theaterState = :region ORDER BY t.theaterId ASC")
    List<Theater> findByTheaterState(@Param("region") String region);

}
