package com.example.project.service;

import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.project.dto.MemberDto;
import com.example.project.entity.Member;
import com.example.project.entity.constant.MemberRole;
import com.example.project.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void registerMember(MemberDto memberDto) {
        if (memberRepository.existsByMemberId(memberDto.getMemberId())) {
            throw new IllegalArgumentException("중복된 아이디입니다.");
        }

        try {
            Member member = Member.builder()
                    .memberId(memberDto.getMemberId())
                    .password(passwordEncoder.encode(memberDto.getPassword()))
                    .name(memberDto.getName())
                    .email(memberDto.getEmail())
                    .gender(memberDto.getGender())
                    .birth(memberDto.getBirth())
                    .phone(memberDto.getPhone()) // 추가된 전화번호
                    .address(memberDto.getAddress())
                    .point(0)
                    .role(MemberRole.MEMBER)
                    .build();

            memberRepository.save(member);
        } catch (Exception e) {
            e.printStackTrace(); // 에러를 출력하여 디버깅
            throw new RuntimeException("회원가입 중 오류가 발생했습니다.");
        }
    }

    @Override
    public boolean validateMember(String memberId, String rawPassword) {
        Optional<Member> optionalMember = memberRepository.findByMemberId(memberId);

        if (optionalMember.isPresent()) {
            Member member = optionalMember.get();
            return passwordEncoder.matches(rawPassword, member.getPassword());
        }
        return false;
    }

}
