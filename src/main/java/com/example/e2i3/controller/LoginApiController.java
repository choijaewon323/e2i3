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

    @PostMapping("/api/login/login")
    public Integer login(MemberDTO memberDTO, HttpServletRequest request) {
        return loginService.login(memberDTO, request);
    }

    @PostMapping("/api/login/save")
    public Integer save(MemberDTO memberDTO) {
        return loginService.register(memberDTO);
    }

    @PostMapping("/api/login/logout")
    public Integer logout(HttpServletRequest request) {
        return loginService.logout(request);
    }
}
