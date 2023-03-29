package com.example.e2i3.controller;

import com.example.e2i3.dto.MemberDTO;
import com.example.e2i3.service.LoginService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class LoginApiController {
    private final LoginService loginService;

    @PostMapping("/api/login")
    public Integer login(MemberDTO memberDTO, HttpServletRequest request) {
        return loginService.login(memberDTO, request);
    }

    @PostMapping("/api/save")
    public Integer save(MemberDTO memberDTO) {
        return loginService.register(memberDTO);
    }

    @PostMapping("/api/logout")
    public Integer logout(MemberDTO memberDTO, HttpServletRequest request) {
        return loginService.logout(memberDTO, request);
    }
}
