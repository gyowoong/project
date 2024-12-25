package com.example.project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.project.entity.MemberFavoriteMovie;

public interface MemberFavoriteMovieRepository extends JpaRepository<MemberFavoriteMovie, Long> {
    @Query("SELECT mfm FROM MemberFavoriteMovie mfm WHERE mfm.member.id = :memberId")
    List<MemberFavoriteMovie> findByMemberId(Long memberId);

    // List<MemberFavoriteMovie> findByMovieId(Long movieId);
}