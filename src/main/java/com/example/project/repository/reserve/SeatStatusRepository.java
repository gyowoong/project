package com.example.project.repository.reserve;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.project.entity.SeatStatus;
import com.example.project.entity.constant.SeatStatusEnum;

public interface SeatStatusRepository extends JpaRepository<SeatStatus, Long> {
    List<SeatStatus> findByAuditorium_Seat(Long auditoriumNo); // 특정 상영관의 모든 좌석 상태 조회

    List<SeatStatus> findBySeat_SeatId(Long seatId); // 특정 좌석의 상태 조회

    List<SeatStatus> findBySeatStatus(SeatStatusEnum status); // 특정 상태의 좌석 조회
}
