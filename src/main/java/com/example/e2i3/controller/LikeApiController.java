package com.example.e2i3.controller;

import com.example.e2i3.dto.LikeDTO;
import com.example.e2i3.service.LikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class LikeApiController {
    private final LikeService likeService;

    @PostMapping("/api/like")
    public Integer pushLike(LikeDTO likeDTO) {
        return likeService.pushLike(likeDTO);
    }

    @DeleteMapping("/api/like")
    public Integer popLike(LikeDTO likeDTO) {
        return likeService.popLike(likeDTO);
    }
}
