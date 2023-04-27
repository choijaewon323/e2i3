package com.example.e2i3.dto;

import com.example.e2i3.entity.Board;
import com.example.e2i3.entity.Comment;
import lombok.*;

@Setter
@Getter
@NoArgsConstructor
public class CommentDTO {
    private Long id;
    private String writer;
    private String content;

    // Response
    @Builder
    public CommentDTO(Long id, String writer, String content) {
        this.id = id;
        this.writer = writer;
        this.content = content;
    }

    public Comment toEntity(Board board) {
        return Comment.builder()
                .writer(writer)
                .content(content)
                .board(board)
                .build();
    }
}
