package com.example.project.dto;

import com.example.project.entity.constant.MemberRole;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MemberDto {

    private Long mid; // Primary Key

    @NotBlank(message = "아이디는 필수 입력요소입니다")
    private String memberId; // 사용자 ID

    @NotBlank(message = "비밀번호는 필수 입력요소입니다")
    private String password; // 비밀번호

    @NotBlank(message = "이름은 필수 입력요소입니다")
    private String name; // 이름

    private String phone; // 연락처

    @NotBlank(message = "성별은 필수 입력요소입니다")
    private String gender; // 성별

    @NotBlank(message = "생년월일은 필수 입력요소입니다")
    private String birth; // 생년월일

    @Email
    @NotBlank(message = "이메일은 필수 입력요소입니다")
    private String email; // 이메일

    private String address; // 주소

    private int point;

    private MemberRole role; // Enum을 String으로 변환하여 전송
}