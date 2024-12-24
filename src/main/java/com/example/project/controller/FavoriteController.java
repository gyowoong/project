package com.example.project.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.project.dto.MovieDto;
import com.example.project.entity.Movie;
import com.example.project.service.FavoriteService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/favorite")
public class FavoriteController {
    @Autowired
    private final FavoriteService favoriteService;

    @PostMapping("/favorite/add")
    public ResponseEntity<String> addFavorite(@RequestParam Long movieId, Principal principal) {
        if (principal == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("로그인이 필요합니다.");
        }

        String memberId = principal.getName();
        favoriteService.addFavorite(memberId, movieId);
        return ResponseEntity.ok("찜하기 완료");
    }

    @GetMapping("/favorite/list")
    public ResponseEntity<List<MovieDto>> getFavoriteList(Principal principal) {
        if (principal == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }

        String memberId = principal.getName();
        List<MovieDto> favorites = favoriteService.getFavorites(memberId);
        return ResponseEntity.ok(favorites);
    }
}