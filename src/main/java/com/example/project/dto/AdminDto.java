package com.example.project.dto;

import com.example.project.admin.Entity.constant.AdminRole;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class AdminDto {
    private Long ano;
    private String userId;
    private String password;
    private AdminRole role;
}
