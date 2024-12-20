package com.example.project.admin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.query.Param;

import com.example.project.admin.Entity.MovieAdd;
import com.example.project.admin.Entity.MovieState;
import com.example.project.admin.Entity.QMovieAdd;
import com.example.project.admin.Entity.QMovieState;
import com.example.project.admin.dto.test.MovieAddDto;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;

import java.util.List;

public interface MovieAddRepository extends JpaRepository<MovieAdd, Long> {
    @Query(value = "SELECT S.STATE, T.THEATER_NAME,T.THEATER_ADD,T.MANAGER FROM THEATER T JOIN THEATER_STATE S ON T.STATE_ID = S.STATE_ID", nativeQuery = true)
    List<Object[]> findByAddList();

    // @Query("SELECT ms.state,ma.name,ma.add,ma.manager FROM MovieAdd ma JOIN
    // ma.movieState ms WHERE ms.state = :state")
    // List<Object[]> findByState(String state);

    // @Query(value = "SELECT ts.STATE, t.THEATER_NAME,t.THEATER_ADD,t.MANAGER FROM
    // THEATER t JOIN THEATER_STATE ts ON t.STATE_ID = ts.STATE_ID WHERE (ts.STATE
    // IS NULL OR ts.STATE LIKE %:state%) and (t.THEATER_NAME IS NULL OR
    // t.THEATER_NAME LIKE %:name%)", nativeQuery = true)
    // List<Object[]> stateAndName(String state, String name);

    @Query(value = "SELECT t,ts FROM THEATER t JOIN t.movieState ts WHERE ts.state LIKE %:state%")
    List<Object[]> stateSearch(String state);

    @Query(value = "SELECT t,ts FROM THEATER t JOIN t.movieState ts WHERE t.name LIKE %:name%")
    List<Object[]> nameSearch(String name);

    @Query(value = "SELECT t, ts FROM THEATER t JOIN t.movieState ts " +
            "WHERE (:state IS NULL OR ts.state LIKE %:state%) " +
            "AND (:name IS NULL OR t.name LIKE %:name%)")
    List<Object[]> stateAndName(String state, String name);

}
