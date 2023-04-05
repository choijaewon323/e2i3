package com.example.e2i3.service;

import com.example.e2i3.dto.MemberDTO;
import com.example.e2i3.entity.Member;
import com.example.e2i3.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;


@RequiredArgsConstructor
@Service
public class MemberService {
    private final MemberRepository memberRepository;

    @Transactional
    public void update(MemberDTO memberDTO) {
        Member member = memberRepository.findById(memberDTO.getId()).orElseThrow();

        member.update(memberDTO);
    }

    // 삭제
    @Transactional
    public Integer deleteById(MemberDTO memberDTO) {
        Optional<Member> byMemberEmail = memberRepository.findByEmail(memberDTO.getEmail());
        if(byMemberEmail.isPresent()){
            //성공
            //비밀번호 확인 조건 설정
            Member memberEntity = byMemberEmail.get();
            Long id = memberEntity.getId();

            String memberPassword = memberEntity.getPassword();
            String memberPassword1 = memberDTO.getPassword();

            if(memberPassword.equals(memberPassword1)){
                //memberRepository.delete(memberEntity);
                memberRepository.deleteById(id);
                return 1;
            }
            else{
                return 0;
            }
        }
        else{
            return 0;
        }
    }
}
