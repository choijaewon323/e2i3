package com.example.e2i3.controller;

import com.example.e2i3.service.HeartService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class HeartApiController {
    private final HeartService heartService;

    @GetMapping("/api/heart/{boardID}/{email}")
    public Integer getHeart(@PathVariable Long boardID, @PathVariable String email) {
        return heartService.getHeart(boardID, email);
    }

    @PostMapping("/api/heart/{boardID}/{email}")
    public Integer pushHeart(@PathVariable Long boardID, @PathVariable String email) {
        return heartService.pushHeart(boardID, email);
    }

    @DeleteMapping("/api/heart/{boardID}/{email}")
    public Integer popHeart(@PathVariable Long boardID, @PathVariable String email) {
        return heartService.popHeart(boardID, email);
    }
}
