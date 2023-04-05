package com.example.e2i3.service;

import com.example.e2i3.dto.CommentDTO;
import com.example.e2i3.entity.Board;
import com.example.e2i3.entity.Comment;
import com.example.e2i3.repository.BoardRepository;
import com.example.e2i3.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class CommentService {
    private final CommentRepository commentRepository;
    private final BoardRepository boardRepository;

    public List<CommentDTO> getComments(Long boardID) {
        Board board = boardRepository.findById(boardID)
                        .orElseThrow();
        List<Comment> comments = commentRepository.findByBoard(board);
        List<CommentDTO> results = new ArrayList<>();

        for (Comment comment : comments) {
            CommentDTO commentDTO = CommentDTO.builder()
                    .writer(comment.getWriter())
                    .content(comment.getContent())
                    .id(comment.getId())
                    .build();
            results.add(commentDTO);
        }

        return results;
    }

    public CommentDTO getComment(Long commentID) {
        Comment comment = commentRepository.findById(commentID)
                .orElseThrow();

        CommentDTO commentDTO = CommentDTO.builder()
                .id(comment.getId())
                .content(comment.getContent())
                .writer(comment.getWriter())
                .build();

        return commentDTO;
    }

    @Transactional
    public void postComment(Long boardID, CommentDTO commentDTO) {
        Board board = boardRepository.findById(boardID)
                        .orElseThrow();

        commentRepository.save(commentDTO.toEntity(board));
    }

    @Transactional
    public void putComment(Long commentID, CommentDTO commentDTO) {
        Comment comment = commentRepository.findById(commentID)
                        .orElseThrow();

        comment.update(commentDTO.getWriter(), commentDTO.getContent());
    }

    @Transactional
    public void deleteComment(Long commentID) {
        commentRepository.deleteById(commentID);
    }
}
