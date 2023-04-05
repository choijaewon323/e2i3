package com.example.e2i3.service;

import com.example.e2i3.dto.MemberDTO;
import com.example.e2i3.entity.Member;
import com.example.e2i3.repository.MemberRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class LoginService {
    private final MemberRepository memberRepository;

    // 성공 : 1, 실패 : 0
    @Transactional
    public Integer register(MemberDTO memberDTO) {
        Boolean isExisted = memberRepository.findByEmail(memberDTO.getEmail()).isPresent();

        // 이미 존재하면 실패
        if (isExisted) {
            return 0;
        }

        memberRepository.save(memberDTO.toEntity());

        // 성공
        return 1;
    }

    // 성공 : 1, 실패 : 0
    public Integer login(MemberDTO memberDTO, HttpServletRequest request) {
        // 아이디가 없으면 실패
        if (memberDTO.getEmail() == null) {
            return 0;
        }

        Boolean isExisted = memberRepository.findByEmail(memberDTO.getEmail()).isPresent();

        // 아이디가 존재하지 않으면 실패
        if (!isExisted) {
            return 0;
        }

        Member member = memberRepository.findByEmail(memberDTO.getEmail()).orElseThrow();

        // 로그인 성공
        if (member.getPassword().equals(memberDTO.getPassword())) {
            HttpSession session = request.getSession();

            // FRONTEND에서 success.email같이 사용가능
            session.setAttribute("success", memberDTO);

            return 1;
        }

        // 비밀번호 틀림
        return 0;
    }

    public Integer logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);

        if (session == null) {
            return 0;
        }

        session.invalidate();
        return 1;
    }
}
