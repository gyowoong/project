package com.example.project.service;

import com.example.project.dto.PeopleDto;
import com.example.project.dto.PageRequestDTO;
import com.example.project.dto.PageResultDTO;
import com.example.project.entity.People;

public interface PeopleService {

    PageResultDTO<PeopleDto, People> getList(PageRequestDTO requestDto);

    public default PeopleDto entityToDto(People people) {
        return PeopleDto.builder()
                .id(people.getId())
                .gender(people.getGender())
                .knownForDepartment(people.getKnownForDepartment())
                .name(people.getName())
                .popularity(people.getPopularity())
                .profilePath(people.getProfilePath())
                .build();
    }

    public default People dtoToEntity(PeopleDto peopleDto) {
        return People.builder()
                .id(peopleDto.getId())
                .gender(peopleDto.getGender())
                .knownForDepartment(peopleDto.getKnownForDepartment())
                .name(peopleDto.getName())
                .popularity(peopleDto.getPopularity())
                .profilePath(peopleDto.getProfilePath())
                .build();
    }

}
