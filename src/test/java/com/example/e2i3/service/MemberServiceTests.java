package com.example.e2i3.service;

import com.example.e2i3.controller.BoardApiController;
import com.example.e2i3.controller.HeartApiController;
import com.example.e2i3.controller.LoginApiController;
import com.example.e2i3.controller.MemberApiController;
import com.example.e2i3.dto.MemberDTO;
import com.example.e2i3.entity.Member;
import com.example.e2i3.repository.BoardRepository;
import com.example.e2i3.repository.HeartRepository;
import com.example.e2i3.repository.MemberRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class MemberServiceTests {
    @Autowired
    BoardApiController boardApiController;
    @Autowired
    MemberApiController memberApiController;
    @Autowired
    LoginApiController loginApiController;
    @Autowired
    HeartApiController heartApiController;

    @Autowired
    BoardService boardService;

    @Autowired
    BoardRepository boardRepository;
    @Autowired
    MemberRepository memberRepository;
    @Autowired
    HeartRepository heartRepository;

    @Test
    void memberSave(){
        MemberDTO memberDTO = MemberDTO.builder().email("1").password("1").build();
        System.out.println(loginApiController.save(memberDTO));
    }
    @Test
    void memberUpdate(){
        MemberDTO memberDTO = MemberDTO.builder().email("1").password("1").build();
        loginApiController.save(memberDTO);

        MemberDTO memberDTO1 = MemberDTO.builder().email("2").password("2").build();
        Member member = memberRepository.findAll().get(0);
        memberApiController.updateMember(member.getId(),memberDTO1);
        System.out.println(memberRepository.findAll().get(0).getEmail());
    }
    @Test
    void memberDelete(){
        MemberDTO memberDTO = MemberDTO.builder().email("1").password("1").build();
        loginApiController.save(memberDTO);

        Member member = memberRepository.findAll().get(0);
        System.out.println(memberApiController.delete(member.getId()));
    }
    @Test
    void memberLogin() throws Exception {
        MemberDTO memberDTO = MemberDTO.builder().email("1").password("1").build();
        System.out.println(loginApiController.save(memberDTO));

        MockHttpServletRequest request = new MockHttpServletRequest();
        request.setRemoteAddr("127.0.0.1");

        Integer result = loginApiController.login(memberDTO, request);

        System.out.println(result);
    }
    @Test
    void memberLogout(){
        MemberDTO memberDTO = MemberDTO.builder().email("1").password("1").build();
        System.out.println(loginApiController.save(memberDTO));

        MockHttpServletRequest request = new MockHttpServletRequest();
        request.setRemoteAddr("127.0.0.1");

        Integer result = loginApiController.login(memberDTO, request);
        System.out.println(result);

        System.out.println(loginApiController.logout(request));
    }
}
