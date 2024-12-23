package com.example.project.repository.movie;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.project.entity.Movie;
import com.example.project.entity.People;

public interface PeopleCustomRepository {

    Page<People> getTotalList(String type, String keyword, Pageable pageable);

    List<People> getDirectorListByMovieId(Long id);

}
