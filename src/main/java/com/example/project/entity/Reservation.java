package com.example.project.entity;

import java.time.LocalDateTime;

import com.example.project.entity.constant.ReserveStatus;
import com.example.project.entity.constant.SeatStatusEnum;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Member member; // 예매한 회원 정보

    private String movieTitle; // 영화 제목
    private LocalDateTime showTime; // 상영 시간

    @Enumerated(EnumType.STRING)
    private SeatStatusEnum seatStatus; // 좌석 상태

    @Enumerated(EnumType.STRING)
    private ReserveStatus reserveStatus; // 예약 상태

    // 기타 필요한 필드들
    private int seatNumber;
}