package com.example.project.repository.reserve;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.project.entity.reserve.Seat;

public interface SeatRepository extends JpaRepository<Seat, Long> {
    // List<Seat> getAuditoriumSeat(Long auditoriumNo);
}
