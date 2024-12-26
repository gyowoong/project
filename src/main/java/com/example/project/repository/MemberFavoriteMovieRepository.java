package com.example.project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.project.entity.MemberFavoriteMovie;

import jakarta.transaction.Transactional;

public interface MemberFavoriteMovieRepository extends JpaRepository<MemberFavoriteMovie, Long> {
    @Query("SELECT mfm FROM MemberFavoriteMovie mfm WHERE mfm.member.id = :memberId")
    List<MemberFavoriteMovie> findByMemberId(Long memberId);

    boolean existsByMemberMidAndMovieId(Long memberId, Long movieId);

    @Transactional
    @Modifying
    @Query("DELETE FROM MemberFavoriteMovie mfm WHERE mfm.member.id = :memberId AND mfm.movie.id = :movieId")
    void deleteByMemberIdAndMovieId(Long memberId, Long movieId);

    // List<MemberFavoriteMovie> findByMovieId(Long movieId);
}