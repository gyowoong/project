package com.example.project.service;

import com.example.project.entity.Reservation;

import java.util.List;

public interface ReservationService {
    List<Reservation> getReservationsByMemberId(Long memberId); // 회원의 예매 내역 조회
}