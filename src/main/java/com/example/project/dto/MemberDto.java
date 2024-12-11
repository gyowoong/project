package com.example.project.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MemberDto {
    private String memberId;
    private String name;
    private String phone;
    private String gender;
    private int age;
    private String email;
    private String list;
    private int points;
    private String reservationHistory;
    private String address;
}