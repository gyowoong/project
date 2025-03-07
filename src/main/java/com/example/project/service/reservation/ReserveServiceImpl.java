package com.example.project.service.reservation;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.project.dto.MovieDto;
import com.example.project.dto.ScreeningDto;
import com.example.project.dto.reserve.ReserveDto;
import com.example.project.dto.reserve.TheaterDto;
import com.example.project.entity.Movie;
import com.example.project.entity.reserve.Reserve;
import com.example.project.entity.reserve.Screening;
import com.example.project.entity.reserve.Theater;
import com.example.project.repository.reserve.ReserveRepository;
import com.example.project.repository.reserve.ScreeningRepository;
import com.example.project.repository.reserve.TheaterRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@RequiredArgsConstructor
@Service
public class ReserveServiceImpl implements ReserveService {
    private final ReserveRepository reserveRepository;
    private final TheaterRepository theaterRepository;
    private final ScreeningRepository screeningRepository;

    @Override
    public Long createReserve(ReserveDto reserveDto) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'createReserve'");
    }

    @Override
    public ReserveDto getReserve(Long reserveId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getReserve'");
    }

    @Override
    public List<ReserveDto> getAllReserves() {
        // Reserve 엔티티 리스트를 조회
        List<Reserve> reserves = reserveRepository.findAll();

        // Reserve 엔티티를 ReserveDto로 변환하여 반환
        return reserves.stream()
                .map(reserve -> entityToDto(reserve))
                .collect(Collectors.toList());
    }

    @Override
    public ReserveDto updateReserve(Long reserveId, ReserveDto reserveDto) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateReserve'");
    }

    @Override
    public void deleteReserve(Long reserveId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteReserve'");
    }

    @Override
    public List<String> getAllRegions() {
        return theaterRepository.findAllRegions();
    }

    @Override
    public List<TheaterDto> getTheatersByRegion(String region) {

        System.out.println("Received region: " + region);
        List<Theater> theaters = theaterRepository.findByTheaterState(region);
        if (theaters.isEmpty()) {
            throw new IllegalArgumentException("No theaters found for the region: " + region);
        }
        System.out.println("Found theaters: " + theaters);
        return theaters.stream()
                .map(theater -> new TheaterDto(
                        theater.getTheaterId(),
                        theater.getTheaterName(),
                        theater.getTheaterState(),
                        theater.getTheaterAdd()))
                .collect(Collectors.toList());

    }

    @Override
    public List<String> getMoviesByTheaterId(Long theaterId) {
        return screeningRepository.findMoviesByTheaterId(theaterId);
    }

    @Override
    public List<ScreeningDto> getScreeningsByMovieTitle(String movieTitle) {
        List<Screening> screenings = screeningRepository.findScreeningsByMovieTitle(movieTitle);
        return screenings.stream()
                .map(screening -> ScreeningDto.builder()
                        .screeningId(screening.getScreeningId())
                        .movieTitle(screening.getMovieTitle())
                        .startTime(screening.getStartTime())
                        .runtime(screening.getRuntime())
                        .openDate(screening.getOpenDate())
                        .closeDate(screening.getCloseDate())
                        .build())
                .collect(Collectors.toList());
    }

}
