package com.example.project.repository;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import com.example.project.entity.Movie;
import com.example.project.entity.QGenre;
import com.example.project.entity.QMovie;
import com.example.project.entity.QMovieGenre;
import com.querydsl.core.Tuple;
import com.querydsl.jpa.JPQLQuery;

import lombok.extern.log4j.Log4j2;

// @Log4j2
// public class MovieRepositoryImpl extends QuerydslRepositorySupport implements
// MovieRepository {

// public MovieRepositoryImpl() {
// super(Movie.class);
// }

// @Override
// public Page<Object[]> getTotalList(String type, String keyword, Long genreId,
// Pageable pageable) {
// QMovie movie = QMovie.movie;
// QMovieGenre movieGenre = QMovieGenre.movieGenre;
// QGenre genre = QGenre.genre;

// JPQLQuery<Movie> query =
// from(movie).leftJoin(movieGenre).on(movie.eq(movieGenre.movie)).leftJoin(genre)
// .on(movieGenre.genre.eq(genre));
// JPQLQuery<Tuple> tuple = query.select(movie.id, movie.title, genre.id,
// genre.name).where(genre.id.eq(genreId));
// List<Tuple> result = tuple.fetch();

// long count = tuple.fetchCount();
// return new PageImpl<>(result.stream().map(t ->
// t.toArray()).collect(Collectors.toList()), pageable,
// count);

// }

// @Override
// public List<String> getGenre(Long id) {
// // TODO Auto-generated method stub
// throw new UnsupportedOperationException("Unimplemented method 'getGenre'");
// }

// @Override
// public List<String> getpeople(Long id) {
// // TODO Auto-generated method stub
// throw new UnsupportedOperationException("Unimplemented method 'getpeople'");
// }

// // @Override
// // public List<String> getGenre(Long id) {
// // // TODO Auto-generated method stub
// // throw new UnsupportedOperationException("Unimplemented method
// 'getGenre'");
// // }

// // @Override
// // public List<String> getpeople(Long id) {
// // // TODO Auto-generated method stub
// // throw new UnsupportedOperationException("Unimplemented method
// 'getpeople'");
// // }

// // @Override
// // public Page<Object[]> getTotalList(String type, String keyword, Long
// genreId,
// // Pageable pageable) {
// // QMovie movie = QMovie.movie;
// // QMovieGenre movieGenre = QMovieGenre.movieGenre;
// // QGenre genre = QGenre.genre;

// // JPQLQuery<Movie> query =
// //
// from(movie).leftJoin(movieGenre).on(movie.eq(movieGenre.movie)).leftJoin(movieGenre)
// // .on(movieGenre.genre.eq(genre));

// // JPQLQuery<Tuple> tuple = query.select(movie.title,
// // genre.name).where(movieGenre.genre.id.eq(genreId));

// // List<Tuple> result = tuple.fetch();
// // long count = tuple.fetchCount();

// // return new PageImpl<>(result.stream().map(t ->
// // t.toArray()).collect(Collectors.toList()), pageable, count);
// // }

// }
