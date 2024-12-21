package com.example.project.dto;

import java.util.Set;

import com.example.project.entity.MoviePeople;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class PeopleDto {
    private Long id;

    private Long gender;
    private String job;
    private String name;
    private Double popularity;
    private String profilePath;

    private Set<MoviePeople> moviePeople;
}
