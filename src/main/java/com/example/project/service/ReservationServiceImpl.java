package com.example.project.service;

import com.example.project.entity.Reservation;
import com.example.project.repository.ReservationRepository;
import com.example.project.service.ReservationService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservationServiceImpl implements ReservationService {

    private final ReservationRepository reservationRepository;

    public ReservationServiceImpl(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    @Override
    public List<Reservation> getReservationsByMemberId(Long memberId) {
        // 특정 회원의 예매 내역을 데이터베이스에서 조회
        return reservationRepository.findByMemberId(memberId);
    }
}