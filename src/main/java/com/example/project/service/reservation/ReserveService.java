package com.example.project.service.reservation;

import java.util.List;
import java.util.Map;

import com.example.project.dto.MovieDto;
import com.example.project.dto.ReserveDto;
import com.example.project.dto.TheaterDto;
import com.example.project.entity.Member;
import com.example.project.entity.Movie;
import com.example.project.entity.reserve.Auditorium;
import com.example.project.entity.reserve.Reserve;
import com.example.project.entity.reserve.Screening;
import com.example.project.entity.reserve.Seat;
import com.example.project.entity.reserve.Theater;

public interface ReserveService {

    // CRUD
    Long createReserve(ReserveDto reserveDto);

    ReserveDto getReserve(Long reserveId);

    List<ReserveDto> getAllReserves();

    ReserveDto updateReserve(Long reserveId, ReserveDto reserveDto);

    void deleteReserve(Long reserveId);

    List<String> getAllRegions();

    List<TheaterDto> getTheatersByRegion(String region);

    // List<MovieDto> getMoviesByTheaterId(Long theaterId);

    List<Screening> getScreenings(Long theaterId, Long movieId);

    default Reserve dtoToEntity(ReserveDto reserveDto) {
        return Reserve.builder()
                .reserveId(reserveDto.getReserveId())
                .reserveNo(reserveDto.getReserveNo())
                .reserveStatus(reserveDto.getStatus())
                .theater(Theater.builder().theaterId(reserveDto.getTheaterId()).build())
                .auditorium(Auditorium.builder().auditoriumNo(reserveDto.getAuditoriumNo()).build())
                .seat(Seat.builder().seatId(reserveDto.getSeatId()).build())
                .member(Member.builder().mid(reserveDto.getMid()).build())
                .movie(Movie.builder().id(reserveDto.getId()).build())
                .build();
    }

    default ReserveDto entityToDto(Reserve reserve) {
        return ReserveDto.builder()
                .reserveId(reserve.getReserveId())
                .reserveNo(reserve.getReserveNo())
                .theaterId(reserve.getTheater().getTheaterId())
                .theaterName(reserve.getTheater().getTheaterName())
                .auditoriumNo(reserve.getAuditorium().getAuditoriumNo())
                .auditoriumName(reserve.getAuditorium().getAuditoriumName())
                .price(reserve.getAuditorium().getPrice())
                .seatId(reserve.getSeat().getSeatId())
                .rowNum(reserve.getSeat().getRowNum())
                .seatNum(reserve.getSeat().getSeatNum())
                .mid(reserve.getMember().getMid())
                .id(reserve.getMovie().getId())
                .title(reserve.getMovie().getTitle())
                .status(reserve.getReserveStatus())
                .regDate(reserve.getRegDate())
                .updateDate(reserve.getUpdateDate())
                .build();

    }
}
