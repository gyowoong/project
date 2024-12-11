package com.example.project.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "회원")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Member {
    @Id
    @Column(name = "회원_아이디")
    private String memberId;

    @Column(name = "비밀번호", nullable = false)
    private String password;

    @Column(name = "이름", nullable = false)
    private String name;

    @Column(name = "전화번호", nullable = false)
    private String phone;

    @Column(name = "성별")
    private String gender;

    @Column(name = "나이")
    private int age;

    @Column(name = "이메일")
    private String email;

    @Column(name = "리스트")
    private String list;

    @Column(name = "포인트")
    private int points;

    @Column(name = "애매내역")
    private String reservationHistory;

    @Column(name = "주소")
    private String address;
}
