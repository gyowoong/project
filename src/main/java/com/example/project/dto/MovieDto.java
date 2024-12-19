package com.example.project.dto;

import java.util.ArrayList;
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
public class MovieDto {
     private Long id;

    private String backdrop_path;
    private Long budget;
    private String homepage;
    // private String origin_country;
    private String originalLanguage;
    private String originalTitle;
    
    private String overview;
    private Double popularity;
    private String posterPath;
    private String releaseDate;
    private Long revenue;
    private Long runtime;
    private String status;
    private String tagline;
    private String title;
    private Double voteAverage;
    private Long voteCount;

    
    private Set<MovieGenre> movieGenres;

    
    private Set<MoviePeople> moviePeople;


    

    

}
