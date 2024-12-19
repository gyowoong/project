package com.example.project.service;

import com.example.project.dto.SeatStatusDto;
import com.example.project.entity.Auditorium;
import com.example.project.entity.Seat;
import com.example.project.entity.SeatStatus;

public interface SeatStatusService {
    default SeatStatus dtoToEntity(SeatStatusDto seatStatusDto) {
        return SeatStatus.builder()
                .seatStatusId(seatStatusDto.getSeatStatusId())
                .seatStatusEnum(seatStatusDto.getStatus())
                .auditorium(Auditorium.builder().auditoriumName(seatStatusDto.getAuditoriumName()).build())
                .seat((Seat.builder().seatId(seatStatusDto.getSeatId()).rowNum(seatStatusDto.getRowNum())
                        .seatNum(seatStatusDto.getSeatNum()).build()))
                .build();
    }

    default SeatStatusDto entityToDto(SeatStatus seatStatus) {
        return SeatStatusDto.builder()
                .seatStatusId(seatStatus.getSeatStatusId())
                .seatId(seatStatus.getSeat().getSeatId())
                .rowNum(seatStatus.getSeat().getRowNum())
                .seatNum(seatStatus.getSeat().getSeatNum())
                .status(seatStatus.getSeatStatusEnum())
                .auditoriumName(seatStatus.getAuditorium().getAuditoriumName())
                .build();
    }
}
