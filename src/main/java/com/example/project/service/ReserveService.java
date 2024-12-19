package com.example.project.service;

import java.util.List;

import com.example.project.dto.ReserveDto;
import com.example.project.entity.Auditorium;
import com.example.project.entity.Member;
import com.example.project.entity.Movie;
import com.example.project.entity.Reserve;
import com.example.project.entity.Seat;
import com.example.project.entity.Theater;

public interface ReserveService {

    // CRUD
    Long createReserve(ReserveDto reserveDto);

    ReserveDto getReserve(Long reserveId);

    List<ReserveDto> getAllReserves();

    ReserveDto updateReserve(Long reserveId, ReserveDto reserveDto);

    void deleteReserve(Long reserveId);

    default Reserve dtoToEntity(ReserveDto reserveDto) {
        return Reserve.builder()
                .reserveId(reserveDto.getReserveId())
                .reserveNo(reserveDto.getReserveNo())
                .reserveStatus(reserveDto.getStatus())
                .theater(Theater.builder().theaterName(reserveDto.getTheaterName()).build())
                .auditorium(Auditorium.builder().auditoriumNo(reserveDto.getAuditoriumNo())
                        .auditoriumName(reserveDto.getAuditoriumName()).price(reserveDto.getPrice()).build())
                .seat(Seat.builder().rowNum(reserveDto.getRowNum()).seatNum(reserveDto.getSeatNum()).build())
                .member(Member.builder().memberId(reserveDto.getMemberId()).build())
                .movie(Movie.builder().title(reserveDto.getTitle()).build())
                .build();
    }

    default ReserveDto entityToDto(Reserve reserve) {
        return ReserveDto.builder()
                .reserveId(reserve.getReserveId())
                .reserveNo(reserve.getReserveNo())
                .theaterName(reserve.getTheater().getTheaterName())
                .auditoriumNo(reserve.getAuditorium().getAuditoriumNo())
                .auditoriumName(reserve.getAuditorium().getAuditoriumName())
                .price(reserve.getAuditorium().getPrice())
                .rowNum(reserve.getSeat().getRowNum())
                .seatNum(reserve.getSeat().getSeatNum())
                .memberId(reserve.getMember().getMemberId())
                .title(reserve.getMovie().getTitle())
                .status(reserve.getReserveStatus())
                .regDate(reserve.getRegDate())
                .updateDate(reserve.getUpdateDate())
                .build();

    }
}
