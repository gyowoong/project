package com.example.project.repository.movie;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.project.entity.Movie;

public interface MovieCustomRepository {
    Page<Object[]> getTotalList(String type, String keyword, String movieList, Long genreId,
            Pageable pageable);

    // List<String> getDirectorList(Long id);

    // List<String> getActorList(Long id);

    // List<String> getGenreList(Long id);

    List<Movie> getMovieListByPersonId(Long id);

    Object[] getMovieDetailById(Long id);
}
