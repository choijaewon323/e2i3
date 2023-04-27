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
import java.util.NoSuchElementException;

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
    public Integer postComment(Long boardID, CommentDTO commentDTO) {
        Board board;

        try {
            board = boardRepository.findById(boardID)
                    .orElseThrow(() -> new NoSuchElementException("Could not find such board"));
        } catch (Exception e) {
            System.out.println(e.getMessage());

            return 0;
        }

        commentRepository.save(commentDTO.toEntity(board));
        return 1;
    }

    @Transactional
    public Integer putComment(Long commentID, CommentDTO commentDTO) {
        Comment comment;

        try {
            comment = commentRepository.findById(commentID)
                    .orElseThrow(() -> new NoSuchElementException("Could not find such comment"));
        } catch (Exception e) {
            System.out.println(e.getMessage());

            return 0;
        }

        comment.update(commentDTO.getWriter(), commentDTO.getContent());
        return 1;
    }

    @Transactional
    public Integer deleteComment(Long commentID)   {
        if (commentID == null || commentID.equals("")) {
            return 0;
        }

        commentRepository.deleteById(commentID);
        return 1;
    }
}
