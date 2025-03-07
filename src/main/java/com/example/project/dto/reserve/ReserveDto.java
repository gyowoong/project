package com.example.project.dto.reserve;

import java.time.LocalDateTime;

import com.example.project.entity.constant.ReserveStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class ReserveDto {
    private Long reserveId; // 예매pk
    private Long reserveNo; // 예매번호

    // 극장 정보
    private Long theaterId;
    private String theaterName;

    // 상영관 정보
    private Long auditoriumNo;
    private String auditoriumName;
    private Long price;

    // 좌석 정보
    private Long seatId;
    private String rowNum;
    private Long seatNum;

    // 회원 정보
    private Long mid;

    // 영화 정보
    private Long id;
    private String title;

    private ReserveStatus status;

    private LocalDateTime regDate;
    private LocalDateTime updateDate;

}
