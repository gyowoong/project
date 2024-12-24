package com.example.project.service;

import java.util.List;

import com.example.project.dto.ReviewDto;
import com.example.project.entity.Review;

public interface ReviewService {
    void saveReview(String content, Long movieId, String username);
}
