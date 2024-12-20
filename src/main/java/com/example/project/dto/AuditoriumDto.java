package com.example.project.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class AuditoriumDto {
    private Long auditoriumNo; // 상영관 ID
    private String auditoriumName; // 상영관 이름
    private Long price; // 상영관 가격
    private Long theaterId;
    private String theaterName; // 극장 이름
}
