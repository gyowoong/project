package com.example.project.repository.movie;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.project.entity.Person;

public interface PersonCustomRepository {

    Page<Person> getTotalList(String type, String keyword, Pageable pageable);

    List<Person> getDirectorListByMovieId(Long id);

}
