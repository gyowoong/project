package com.example.project.dto.reserve;

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

    private Long sno;
    private String state;

    public TheaterDto(String theaterName, String theaterAdd, String state) {
        this.theaterName = theaterName;
        this.theaterAdd = theaterAdd;
        this.state = state;
    }

    public TheaterDto(Long theaterId, String theaterName, String theaterAdd, String state) {
        this.theaterName = theaterName;
        this.theaterAdd = theaterAdd;
        this.state = state;
        this.theaterId = theaterId;

    }
}
