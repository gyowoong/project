// package com.example.project.service.reservation;

// import com.example.project.dto.AuditoriumDto;
// import com.example.project.entity.Auditorium;
// import com.example.project.entity.Theater;

// public interface AuditoriumService {
// default Auditorium dtoToEntity(AuditoriumDto auditoriumDto) {
// return Auditorium.builder()
// .auditoriumNo(auditoriumDto.getAuditoriumNo())
// .auditoriumName(auditoriumDto.getAuditoriumName())
// .price(auditoriumDto.getPrice())
// .theater(Theater.builder().theaterId(auditoriumDto.getTheaterId()).build())
// .build();
// }

// default AuditoriumDto entityToDto(Auditorium auditorium) {
// return AuditoriumDto.builder()
// .auditoriumNo(auditorium.getAuditoriumNo())
// .auditoriumName(auditorium.getAuditoriumName())
// .price(auditorium.getPrice())
// .theaterId(auditorium.getTheater().getTheaterId())
// .theaterName(auditorium.getTheater().getTheaterName())
// .build();
// }
// }
