package com.example.e2i3.service;

import com.example.e2i3.entity.Heart;
import com.example.e2i3.entity.Member;
import com.example.e2i3.repository.HeartRepository;
import com.example.e2i3.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;


@RequiredArgsConstructor
@Service
public class MemberService {
    private final MemberRepository memberRepository;
    private  final HeartRepository heartRepository;

    @Transactional
    public void update(Long id) {
        Member member = memberRepository.findById(id).orElseThrow();

        member.update(member.toMemberDTO());
    }

    // 삭제
    @Transactional
    public Integer deleteById(Long id) {
        // 추가 예외처리 필요
        Optional<Member> byId = memberRepository.findById(id);
        if(byId.isPresent()){
            List<Heart> byMember = heartRepository.findByMember(byId.get());
            for(Heart heart : byMember){
                heart.getBoard().updateHeart(false);
                heartRepository.deleteById(heart.getId());
            }
            memberRepository.deleteById(id);
            return 1;
        }else{
            return 0;
        }
    }
}
