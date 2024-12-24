package com.example.project.service;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.project.dto.PeopleDto;
import com.example.project.dto.PageRequestDTO;
import com.example.project.dto.PageResultDTO;
import com.example.project.entity.People;
import com.example.project.repository.movie.PeopleRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@RequiredArgsConstructor
@Service
public class PeopleServiceImpl implements PeopleService {

    private final PeopleRepository peopleRepository;

    @Override
    public PageResultDTO getList(PageRequestDTO requestDto) {
        // 페이지 나누기 개념 추가
        Pageable pageable = requestDto.getPageable(Sort.by("popularity").descending());
        Page<People> peoples = peopleRepository.getTotalList(requestDto.getType(),
                requestDto.getKeyword(), pageable);
        Function<People, PeopleDto> function = (en -> entityToDto(en));

        return new PageResultDTO<>(peoples, function);
        // return null;
    }

    @Override
    public PeopleDto read(Long id) {
        return entityToDto(peopleRepository.findById(id).get());
    }

    @Override
    public List<PeopleDto> getDirectorListByMovieId(Long id) {
        List<PeopleDto> peopleDtos = new ArrayList<>();
        peopleRepository.getDirectorListByMovieId(id).stream().forEach(person -> {
            peopleDtos.add(entityToDto(person));
        });
        return peopleDtos;
    }

}
