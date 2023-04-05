package com.example.e2i3.service;

import com.example.e2i3.dto.CommentDTO;
import com.example.e2i3.entity.Board;
import com.example.e2i3.entity.Comment;
import com.example.e2i3.repository.BoardRepository;
import com.example.e2i3.repository.CommentRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class CommentServiceTests {
    @Autowired
    CommentService commentService;
    @Autowired
    CommentRepository commentRepository;
    @Autowired
    BoardRepository boardRepository;

    @AfterEach
    void afterEach() {
        commentRepository.deleteAll();
        boardRepository.deleteAll();
    }

    @Test
    void getCommentsTest() {
        Board board = Board.builder()
                .content("게시글 내용")
                .writer("작성자")
                .title("제목")
                .build();

        boardRepository.save(board);

        CommentDTO commentDTO = CommentDTO.builder()
                .writer("작성자1")
                .content("댓글 내용1")
                .build();
        CommentDTO commentDTO2 = CommentDTO.builder()
                .writer("작성자2")
                .content("댓글 내용2")
                .build();
        commentRepository.save(commentDTO.toEntity(board));
        commentRepository.save(commentDTO2.toEntity(board));

        // test
        List<CommentDTO> comments = commentService.getComments(board.getId());

        assertThat(comments.size()).isEqualTo(2);
        assertThat(comments.get(0).getContent()).isEqualTo("댓글 내용1");
    }

    @Test
    void getCommentTest() {
        Board board = Board.builder()
                .content("게시글 내용")
                .writer("작성자")
                .title("제목")
                .build();
        boardRepository.save(board);

        CommentDTO commentDTO = CommentDTO.builder()
                .writer("작성자")
                .content("댓글 내용")
                .build();
        Comment comment = commentRepository.save(commentDTO.toEntity(board));

        // test
        commentDTO = commentService.getComment(comment.getId());

        assertThat(commentDTO.getContent()).isEqualTo("댓글 내용");
    }

    @Test
    void postCommentTest() {
        Board board = Board.builder()
                .content("게시글 내용")
                .writer("작성자")
                .title("제목")
                .build();
        boardRepository.save(board);

        CommentDTO commentDTO = CommentDTO.builder()
                .writer("작성자")
                .content("댓글 내용")
                .build();

        // test
        commentService.postComment(board.getId(), commentDTO);

        assertThat(commentRepository.findAll().get(0).getContent()).isEqualTo("댓글 내용");
    }

    @Test
    void putCommentTest() {
        Board board = Board.builder()
                .content("게시글 내용")
                .writer("작성자")
                .title("제목")
                .build();
        boardRepository.save(board);

        CommentDTO commentDTO = CommentDTO.builder()
                .writer("작성자")
                .content("댓글 내용")
                .build();

        Comment comment = commentRepository.save(commentDTO.toEntity(board));

        commentDTO = CommentDTO.builder()
                .writer("수정된 작성자")
                .content("수정된 댓글")
                .build();

        // test
        commentService.putComment(comment.getId(), commentDTO);

        assertThat(commentRepository.findAll().get(0).getContent()).isEqualTo("수정된 댓글");
    }

    @Test
    void deleteCommentTest() {
        Board board = Board.builder()
                .content("게시글 내용")
                .writer("작성자")
                .title("제목")
                .build();
        boardRepository.save(board);

        CommentDTO commentDTO = CommentDTO.builder()
                .writer("작성자")
                .content("댓글 내용")
                .build();

        Comment comment = commentRepository.save(commentDTO.toEntity(board));

        // test
        commentService.deleteComment(comment.getId());

        assertThat(commentRepository.findAll().size()).isEqualTo(0);
    }
}
