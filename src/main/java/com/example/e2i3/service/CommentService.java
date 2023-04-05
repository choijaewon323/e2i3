package com.example.e2i3.service;

import com.example.e2i3.dto.CommentDTO;
import com.example.e2i3.entity.Board;
import com.example.e2i3.entity.Comment;
import com.example.e2i3.repository.BoardRepository;
import com.example.e2i3.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RestController;

import java.util.NoSuchElementException;

@RequiredArgsConstructor
@RestController
public class CommentService {
    private final CommentRepository commentRepository;
    private final BoardRepository boardRepository;

    @Transactional
    public void postComment(CommentDTO commentDTO) {
        Board board = boardRepository.findById(commentDTO.getBoard_id())
                .orElseThrow();

        commentRepository.save(commentDTO.toEntity(board));
    }

    @Transactional
    public void putComment(CommentDTO commentDTO) {
        Comment comment = commentRepository.findById(commentDTO.getId())
                .orElseThrow(() -> new NoSuchElementException("Could not find such comment"));

        comment.update(comment.getWriter(), comment.getContent());
    }

    @Transactional
    public void deleteComment(CommentDTO commentDTO) {
        Comment comment = commentRepository.findById(commentDTO.getId())
                .orElseThrow(() -> new NoSuchElementException("Could not find such comment"));

        commentRepository.deleteById(comment.getId());
    }


}
