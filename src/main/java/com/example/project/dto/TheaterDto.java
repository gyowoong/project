package com.example.project.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class TheaterDto {

    private Long theaterId;

    private String theaterName;

    private String theaterAdd;

    private String theaterState;

}
