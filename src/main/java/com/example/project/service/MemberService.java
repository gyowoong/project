package com.example.project.service;

import com.example.project.dto.MemberDto;

public interface MemberService {

    void registerMember(MemberDto memberDto);

    boolean validateMember(String memberId, String password);

}