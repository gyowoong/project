package com.example.project.service;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.project.dto.PersonDto;
import com.example.project.dto.PageRequestDTO;
import com.example.project.dto.PageResultDTO;
import com.example.project.entity.Person;
import com.example.project.repository.movie.PersonRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@RequiredArgsConstructor
@Service
public class PersonServiceImpl implements PersonService {

    private final PersonRepository peopleRepository;

    @Override
    public PageResultDTO getList(PageRequestDTO requestDto) {
        // 페이지 나누기 개념 추가
        Pageable pageable = requestDto.getPageable(Sort.by("popularity").descending());
        Page<Person> peoples = peopleRepository.getTotalList(requestDto.getType(),
                requestDto.getKeyword(), pageable);
        Function<Person, PersonDto> function = (en -> entityToDto(en));

        return new PageResultDTO<>(peoples, function);
        // return null;
    }

    @Override
    public PersonDto read(Long id) {
        return entityToDto(peopleRepository.findById(id).get());
    }

    @Override
    public List<PersonDto> getDirectorListByMovieId(Long id) {
        List<PersonDto> peopleDtos = new ArrayList<>();
        peopleRepository.getDirectorListByMovieId(id).stream().forEach(person -> {
            peopleDtos.add(entityToDto(person));
        });
        return peopleDtos;
    }

}
