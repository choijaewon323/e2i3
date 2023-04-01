package com.example.e2i3.controller;

import com.example.e2i3.dto.MemberDTO;
import com.example.e2i3.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PutMapping;
import com.example.e2i3.service.LoginService;
import com.example.e2i3.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class MemberApiController {
    private final MemberService memberService;
    
    @PutMapping("/api/member")
    public void updateMember(MemberDTO memberDTO) {
        memberService.update(memberDTO);
    }

    @DeleteMapping("/api/delete")
    public Integer delete(MemberDTO memberDTO){
        return memberService.deleteById(memberDTO);
    }
}
