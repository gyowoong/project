package com.example.project.controller;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.project.dto.MovieDto;
import com.example.project.dto.reserve.TheaterDto;
import com.example.project.entity.Movie;
import com.example.project.entity.reserve.Screening;
import com.example.project.entity.reserve.Theater;
import com.example.project.service.reservation.ReserveService;
import com.example.project.service.reservation.ScreeningService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RequiredArgsConstructor
@Log4j2
@Controller
@RequestMapping("/reservation")
public class ReserveController {
    private final ReserveService reserveService;

    @GetMapping
    public String getReservationPage(Model model) {
        // 지역 목록 제공
        List<String> regions = reserveService.getAllRegions();
        if (regions == null || regions.isEmpty()) {
            System.out.println("지역값이 없습니다");
        } else {
            System.out.println("Regions: " + regions);
        }
        model.addAttribute("regions", regions);
        return "/movie/reservation";
    }

    @GetMapping("/theaters")
    public ResponseEntity<List<TheaterDto>> getTheatersByRegion(@RequestParam String region) {
        List<TheaterDto> theaters = reserveService.getTheatersByRegion(region);
        if (theaters == null || theaters.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.emptyList());
        }
        return ResponseEntity.ok(theaters);
    }

    // @GetMapping("/movies")
    // public ResponseEntity<List<MovieDto>> getMoviesByTheater(@RequestParam Long
    // theaterId) {
    // List<MovieDto> movies = reserveService.getMoviesByTheater(theaterId);
    // return ResponseEntity.ok(movies);
    // }

    @GetMapping("/screenings")
    public ResponseEntity<List<Screening>> getScreenings(@RequestParam Long theaterId, @RequestParam Long movieId) {
        List<Screening> screenings = reserveService.getScreenings(theaterId, movieId);
        if (screenings.isEmpty()) {
            return ResponseEntity.noContent().build(); // 204 No Content
        }
        return ResponseEntity.ok(screenings); // 200 OK
    }

}