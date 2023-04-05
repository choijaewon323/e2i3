package com.example.e2i3.controller;

import com.example.e2i3.dto.CommentDTO;
import com.example.e2i3.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class CommentApiController {
    private final CommentService commentService;

    @GetMapping("/api/comment/{boardID}")
    public List<CommentDTO> getComments(@PathVariable Long boardID) {
        return commentService.getComments(boardID);
    }

    @GetMapping("/api/comment/{commentID}")
    public CommentDTO getComment(@PathVariable Long commentID) {
        return commentService.getComment(commentID);
    }

    @PostMapping("/api/comment/{boardID}")
    public void postComment(@PathVariable Long boardID, CommentDTO commentDTO) {
        commentService.postComment(boardID, commentDTO);
    }

    @PutMapping("/api/comment/{commentID}")
    public void putComment(@PathVariable Long commentID, CommentDTO commentDTO) {
        commentService.putComment(commentID, commentDTO);
    }

    @DeleteMapping("/api/comment/{commentID}")
    public void deleteComment(@PathVariable Long commentID) {
        commentService.deleteComment(commentID);
    }
}
