package com.example.project.repository;

import com.example.project.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    List<Reservation> findByMemberId(Long memberId); // 특정 회원의 예매 내역 조회
}