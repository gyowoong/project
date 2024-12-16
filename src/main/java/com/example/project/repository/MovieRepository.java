package com.example.project.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import com.example.project.entity.Movie;
import com.example.project.entity.QMovie;
import com.example.project.entity.QPeople;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;

public interface MovieRepository extends JpaRepository<Movie, Long>, QuerydslPredicateExecutor<Movie> {

    @Query("SELECT g.name FROM Movie m JOIN MovieGenre mg ON m.id = mg.movie.id JOIN Genre g ON mg.genre.id = g.id WHERE m.id = :id")
    List<String> getGenre(Long id);

    @Query("SELECT p.name FROM Movie m JOIN MoviePeople mp ON m.id = mp.movie.id JOIN People p ON mp.people.id = p.id WHERE m.id = :id")
    List<String> getpeople(Long id);

    default Predicate makePredicate(String type, String keyword) {
        BooleanBuilder builder = new BooleanBuilder();
        QMovie qMovie = QMovie.movie;
        QPeople qPeople = QPeople.people;

        // id > 0: range scan
        builder.and(qMovie.id.gt(0L));

        if (type == null)
            return builder;

        // 영화
        if (type.contains("m")) {
            builder.and(qMovie.title.contains(keyword));
        }
        // 제목
        else if (type.contains("p")) {
            builder.and(qPeople.name.contains(keyword));
        }

        return builder;
    }

    // Page<Object[]> getTotalList(String type, String keyword, Long genreId,
    // Pageable pageable);

}

// public interface MovieRepository {

// @Query("SELECT g.name FROM Movie m JOIN MovieGenre mg ON m.id = mg.movie.id
// JOIN Genre g ON mg.genre.id = g.id WHERE m.id = :id")
// List<String> getGenre(Long id);

// @Query("SELECT p.name FROM Movie m JOIN MoviePeople mp ON m.id = mp.movie.id
// JOIN People p ON mp.people.id = p.id WHERE m.id = :id")
// List<String> getpeople(Long id);

// Page<Object[]> getTotalList(String type, String keyword, Long genreId,
// Pageable pageable);

// // Page<Object[]> getTotalList(String type, String keyword, Long genreId,
// // Pageable pageable);

// }
