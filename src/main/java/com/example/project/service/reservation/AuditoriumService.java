package com.example.project.service.reservation;

import com.example.project.dto.AuditoriumDto;
import com.example.project.entity.Auditorium;

public interface AuditoriumService {
    default Auditorium dtoToEntity(AuditoriumDto auditoriumDto) {
        return Auditorium.builder()
                .auditoriumNo(auditoriumDto.getAuditoriumNo())
                .auditoriumName(auditoriumDto.getAuditoriumName())
                .price(auditoriumDto.getPrice())
                .build();
    }

    default AuditoriumDto entityToDto(Auditorium auditorium) {
        return AuditoriumDto.builder()
                .auditoriumNo(auditorium.getAuditoriumNo())
                .auditoriumName(auditorium.getAuditoriumName())
                .price(auditorium.getPrice())
                .theaterName(auditorium.getTheater().getTheaterName())
                .build();
    }
}
