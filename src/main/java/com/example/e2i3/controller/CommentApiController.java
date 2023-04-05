package com.example.e2i3.controller;

import com.example.e2i3.dto.CommentDTO;
import com.example.e2i3.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class CommentApiController {
    private final CommentService commentService;

    @PostMapping("/api/comment")
    public void postComment(CommentDTO commentDTO) {
        commentService.postComment(commentDTO);
    }

    @PutMapping("/api/comment")
    public void putComment(CommentDTO commentDTO) {
        commentService.putComment(commentDTO);
    }

    @DeleteMapping("/api/comment")
    public void deleteComment(CommentDTO commentDTO) {
        commentService.deleteComment(commentDTO);
    }
}
