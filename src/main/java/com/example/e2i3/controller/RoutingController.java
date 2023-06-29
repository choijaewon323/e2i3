package com.example.e2i3.controller;

import com.example.e2i3.service.BoardService;
import com.example.e2i3.service.LoginService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


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

    @GetMapping("/login1")
    public String getLogin1() {
        return "login1";
    }

    @GetMapping("/main1")
    public String getMain1(Model model, HttpServletRequest request) {
        HttpSession session = request.getSession(false);

        if (session == null) {
            return "login1";
        }

        Object obj = session.getAttribute("success");

        if (obj == null) {
            return "login1";
        }

        model.addAttribute("boardList", boardService.list());
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
    public String getBoard() {
        return "board";
    }

    @GetMapping("/club")
    public String getClub() {
        return "club";
    }

    @GetMapping("/job")
    public String getJob() {
        return "job";
    }

    @GetMapping("/mkcf")
    public String getMkcf() {
        return "mkcf";
    }

    @GetMapping("/sellgift")
    public String getSellGift() {
        return "sellgift";
    }

    @GetMapping("/watch")
    public String getWatch() {
        return "watch";
    }

    @GetMapping("/write")
    public String getWrite() {
        return "write";
    }
}
