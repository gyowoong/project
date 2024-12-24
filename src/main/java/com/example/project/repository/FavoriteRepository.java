package com.example.project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.project.entity.Favorite;
import com.example.project.entity.Member;
import com.example.project.entity.Movie;

public interface FavoriteRepository extends JpaRepository<Favorite, Long> {
    List<Favorite> findByMember(Member member);

    List<Favorite> findByMember_MemberId(String memberId);

    boolean existsByMemberAndMovie(Member member, Movie movie);
}