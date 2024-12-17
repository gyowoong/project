package com.example.project.dto;

import java.util.Set;

import com.example.project.entity.MovieGenre;
import com.example.project.entity.MoviePeople;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Column;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class GenreDto {
    private Long id;

    private String name;
}
