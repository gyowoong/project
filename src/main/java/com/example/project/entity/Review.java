package com.example.project.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "회원_리뷰")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Review {
    @Id
    @SequenceGenerator(name = "movie_review_seq_gen", sequenceName = "movie_review_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "movie_review_seq_gen")
    @Column(name = "리뷰번호")
    private Long reviewId;

    @ManyToOne
    @JoinColumn(name = "회원_아이디", nullable = false)
    private Member member;

    @Column(name = "내용", nullable = false)
    private String content;

    @Column(name = "평점", nullable = false)
    private int rating;
}