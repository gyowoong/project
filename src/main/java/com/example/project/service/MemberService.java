package com.example.project.service;

import com.example.project.dto.MemberDto;
import com.example.project.entity.Member;
import com.example.project.entity.constant.MemberRole;

public interface MemberService {

    default Member dtoToEntity(MemberDto memberDto) {
        return Member.builder()
                .memberId(memberDto.getMemberId())
                .password(memberDto.getPassword())
                .name(memberDto.getName())
                .phone(memberDto.getPhone())
                .gender(memberDto.getGender())
                .birth(memberDto.getBirth())
                .email(memberDto.getEmail())
                .point(memberDto.getPoint())
                .address(memberDto.getAddress())
                .role(MemberRole.MEMBER)
                .build();
    }

    default MemberDto entityToDto(Member member) {
        return MemberDto.builder()
                .memberId(member.getMemberId())
                .password(member.getPassword())
                .name(member.getName())
                .phone(member.getPhone())
                .gender(member.getGender())
                .birth(member.getBirth())
                .email(member.getEmail())
                .point(member.getPoint())
                .address(member.getAddress())
                .role(MemberRole.MEMBER)
                .build();
    }
}