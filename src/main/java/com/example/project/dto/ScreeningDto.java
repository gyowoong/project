package com.example.project.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class ScreeningDto {
    private Long screeningId;
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    private Long theaterId;
    private String theaterName;
    private Long auditoriumNo;
    private String auditoriumName;

    private Long movieId;
    private String movieTitle;
    private Long runtime;
}
