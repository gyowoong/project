package com.example.project.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class MoviePeopleDto {

    private Long id;

    private String character;

    private String role;

    private Long peopleId;

    private Long movieId;

}
