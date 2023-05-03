package com.example.e2i3.service;

import com.example.e2i3.dto.MemberDTO;
import com.example.e2i3.entity.Member;
import com.example.e2i3.repository.MemberRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequestWrapper;
import org.apache.coyote.Request;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class LoginServiceTests {
    @Autowired
    LoginService loginService;
    @Autowired
    MemberRepository memberRepository;

    @AfterEach
    void afterEach() {
        memberRepository.deleteAll();
    }

    @Test
    void registerTest() {
        MemberDTO memberDTO = MemberDTO.builder()
                .email("testEmail")
                .password("password")
                .build();

        MemberDTO memberDTO1 = MemberDTO.builder()
                .email("testEmail")
                .password("1234")
                .build();

        // test
        Integer result1 = loginService.register(memberDTO);

        assertThat(result1).isEqualTo(1);

        Integer result2 = loginService.register(memberDTO1);
        assertThat(result2).isEqualTo(0);
    }

    @Test
    void loginTest() {
        memberRepository.save(Member.builder()
                .email("email")
                .password("1234")
                .build());

        // test
        MemberDTO test1 = MemberDTO.builder()
                .email("email1")
                .password("1234")
                .build();


        loginService.login(test1, null);
    }
}
