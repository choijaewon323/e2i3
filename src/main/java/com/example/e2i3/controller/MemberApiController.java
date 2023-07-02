package com.example.e2i3.controller;

import com.example.e2i3.dto.MemberDTO;
import com.example.e2i3.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import com.example.e2i3.service.LoginService;
import com.example.e2i3.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

@RequiredArgsConstructor
@RestController
public class MemberApiController {
    private final MemberService memberService;
    
    @PutMapping("/api/member/{id}")
    public void updateMember(@PathVariable Long id, MemberDTO memberDTO) {
        memberService.update(id, memberDTO);
    }

    @DeleteMapping("/api/member/{id}")
    public Integer delete(@PathVariable Long id){
        return memberService.deleteById(id);
    }
}
