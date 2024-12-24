package com.example.project.admin.service;

import com.example.project.entity.test.UserEntity;

public interface UserAdminService {

    // 로그인 데이터 담기
    UserEntity login(String username, String password);
}
