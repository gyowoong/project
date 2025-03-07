package com.example.project.dto.reserve;

import com.example.project.entity.constant.SeatStatusEnum;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class SeatStatusDto {
    private Long seatStatusId;
    private Long seatId;
    private String rowNum;
    private Long seatNum;
    private SeatStatusEnum status;
    private String auditoriumName;
}
