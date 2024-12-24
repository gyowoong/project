package com.example.project.admin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.project.entity.reserve.Theater;

import java.util.List;

public interface MovieAddRepository extends JpaRepository<Theater, Long> {
    @Query(value = "SELECT t,s FROM THEATER T JOIN t.movieState s WHERE T.movieState= S.sno", nativeQuery = true)
    List<Object[]> findByAddList();

    @Query(value = "SELECT t, ts FROM Theater t JOIN t.movieState ts " +
            "WHERE (:state IS NULL OR ts.state LIKE %:state%) " +
            "AND (:theaterName IS NULL OR t.theaterName LIKE %:theaterName%)")
    List<Object[]> stateAndName(String state, String theaterName);

}
