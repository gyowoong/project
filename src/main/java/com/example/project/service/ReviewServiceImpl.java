package com.example.project.service;

import org.springframework.stereotype.Service;

import com.example.project.entity.Member;
import com.example.project.entity.Movie;
import com.example.project.entity.Review;
import com.example.project.repository.MemberRepository;
import com.example.project.repository.ReviewRepository;
import com.example.project.repository.movie.MovieRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@RequiredArgsConstructor
@Service
public class ReviewServiceImpl implements ReviewService {
    private final ReviewRepository reviewRepository;
    private final MemberRepository memberRepository; // Member용 Repository 필요
    private final MovieRepository movieRepository; // Movie용 Repository 필요

    @Override
    public void saveReview(String content, Long movieId, String username) {
        Member member = memberRepository.findByMemberId(username)
                .orElseThrow(() -> new IllegalArgumentException("유효하지 않은 사용자입니다."));
        Movie movie = movieRepository.findById(movieId)
                .orElseThrow(() -> new IllegalArgumentException("유효하지 않은 영화입니다."));

        Review review = Review.builder()
                .content(content)
                .member(member)
                .movie(movie)
                .build();

        reviewRepository.save(review);
    }

}
