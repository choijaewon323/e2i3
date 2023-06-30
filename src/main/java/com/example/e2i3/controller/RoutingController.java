package com.example.e2i3.controller;

import com.example.e2i3.dto.BoardDTO;
import com.example.e2i3.dto.MemberDTO;
import com.example.e2i3.service.BoardService;
import com.example.e2i3.service.LoginService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;


@RequiredArgsConstructor
@Controller
public class RoutingController {
    private final BoardService boardService;
    private final LoginService loginService;

    @GetMapping("/")
    public String getRoot(HttpServletRequest request) {
        HttpSession session = request.getSession(false);

        if (session == null) {
            return "login1";
        }

        Object obj = session.getAttribute("success");

        if (obj == null) {
            return "login1";
        }

        return "main1";
    }

    @GetMapping("/login")
    public String getLogin1() {
        return "login1";
    }

    @GetMapping("/main")
    public String getMain1(Model model, HttpServletRequest request) {
        HttpSession session = request.getSession(false);

        if (session == null) {
            return "login1";
        }

        Object obj = session.getAttribute("success");

        if (obj == null) {
            return "login1";
        }

        int limit = -1;
        List<BoardDTO> list = boardService.list();
        List<BoardDTO> temp = new ArrayList<>();

        if (list.size() >= 30) {
            limit = 30;
        } else {
            limit = list.size();
        }

        for (int i = 0; i < limit; i++) {
            temp.add(list.get(i));
        }

        model.addAttribute("boardList", temp);
        model.addAttribute("board1", boardService.list().get(0));
        model.addAttribute("board2", boardService.list().get(1));
        model.addAttribute("board3", boardService.list().get(2));

        return "main1";
    }

    @GetMapping("/sign_inn")
    public String getSign_inn() {
        return "sign_inn";
    }

    @GetMapping("/board")
    public String getBoard(Model model, HttpServletRequest request) {
        HttpSession session = request.getSession(false);

        if (session == null) {
            return "login1";
        }

        Object obj = session.getAttribute("success");

        if (obj == null) {
            return "login1";
        }

        int limit = -1;
        List<BoardDTO> list = boardService.list();

        if (list.size() >= 8) {
            limit = 8;
        } else {
            limit = list.size();
        }

        for (int i = 0; i < limit; i++) {
            model.addAttribute("board" + i, list.get(i));
        }

        return "board";
    }

    @GetMapping("/club")
    public String getClub(Model model, HttpServletRequest request) {
        HttpSession session = request.getSession(false);

        if (session == null) {
            return "login1";
        }

        Object obj = session.getAttribute("success");

        if (obj == null) {
            return "login1";
        }

        int limit = -1;
        List<BoardDTO> list = boardService.list();

        if (list.size() >= 8) {
            limit = 8;
        } else {
            limit = list.size();
        }

        for (int i = 0; i < limit; i++) {
            model.addAttribute("board" + i, list.get(i));
        }

        return "club";
    }

    @GetMapping("/job")
    public String getJob(Model model, HttpServletRequest request) {
        HttpSession session = request.getSession(false);

        if (session == null) {
            return "login1";
        }

        Object obj = session.getAttribute("success");

        if (obj == null) {
            return "login1";
        }

        int limit = -1;
        List<BoardDTO> list = boardService.list();

        if (list.size() >= 8) {
            limit = 8;
        } else {
            limit = list.size();
        }

        for (int i = 0; i < limit; i++) {
            model.addAttribute("board" + i, list.get(i));
        }

        return "job";
    }

    @GetMapping("/sellgift")
    public String getSellGift(Model model, HttpServletRequest request) {
        HttpSession session = request.getSession(false);

        if (session == null) {
            return "login1";
        }

        Object obj = session.getAttribute("success");

        if (obj == null) {
            return "login1";
        }

        int limit = -1;
        List<BoardDTO> list = boardService.list();

        if (list.size() >= 8) {
            limit = 8;
        } else {
            limit = list.size();
        }

        for (int i = 0; i < limit; i++) {
            model.addAttribute("board" + i, list.get(i));
        }

        return "sellgift";
    }

    @GetMapping("/detail/{boardID}")
    public String getWatch(Model model, @PathVariable Long boardID, HttpServletRequest request) {
        HttpSession session = request.getSession(false);

        if (session == null) {
            return "login1";
        }

        Object obj = session.getAttribute("success");

        if (obj == null) {
            return "login1";
        }

        model.addAttribute("board", boardService.findById(boardID));

        int limit = -1;
        List<BoardDTO> list = boardService.list();

        if (list.size() >= 11) {
            limit = 11;
        } else {
            limit = list.size();
        }

        for (int i = 0; i < limit; i++) {
            model.addAttribute("board" + i, list.get(i));
        }

        return "watch";
    }

    @GetMapping("/write")
    public String getWrite(Model model, HttpServletRequest request) {
        HttpSession session = request.getSession(false);

        if (session == null) {
            return "login1";
        }

        Object obj = session.getAttribute("success");

        if (obj == null) {
            return "login1";
        }

        session = request.getSession(false);
        MemberDTO memberDTO = (MemberDTO) session.getAttribute("success");

        model.addAttribute("email", memberDTO.getEmail());

        return "write";
    }
}
