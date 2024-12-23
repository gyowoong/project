package com.example.project.entity.reserve;

import com.example.project.entity.BaseEntity;
import com.example.project.entity.Member;
import com.example.project.entity.Movie;
import com.example.project.entity.constant.ReserveStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString(exclude = { "theater", "auditorium", "member", "movie", "seat" })
@Entity
public class Reserve extends BaseEntity {
    @SequenceGenerator(name = "reserve_seq_gen", sequenceName = "reserve_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "reserve_seq_gen")
    @Id
    private Long reserveId; // 예매번호

    @Column(nullable = false)
    private Long reserveNo; // 예매번호

    @Enumerated(EnumType.STRING)
    private ReserveStatus reserveStatus; // 예매상태

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "theater_id", nullable = false)
    private Theater theater; // 극장 정보

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "auditorium_No", nullable = false)
    private Auditorium auditorium; // 상영관 정보

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "seat_id", nullable = false)
    private Seat seat; // 좌석

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mid", nullable = false)
    private Member member; // 회원 정보

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "movie_id", nullable = false)
    private Movie movie; // 영화 정보

}
