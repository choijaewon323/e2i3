package com.example.e2i3.controller;

import com.example.e2i3.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@RequiredArgsConstructor
@Controller
public class RoutingController {
    private final BoardService boardService;

    @GetMapping("/login1")
    public String getLogin1() {
        return "login1";
    }

    @GetMapping("/main1")
    public String getMain1(Model model) {
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
}
