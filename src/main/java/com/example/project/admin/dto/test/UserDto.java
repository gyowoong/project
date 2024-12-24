package com.example.project.admin.dto.test;

import java.time.LocalDateTime;

import com.example.project.admin.Entity.constant.AdminRole;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDto {
    private String userId;
    private String password;

    private String name;

    private String addr;

    private String telNo;

    private String email;

    private int point;

    private boolean reser;

    private int gender;

    private String brith;

    private LocalDateTime lastLogin;

    private AdminRole adminRole;
}
