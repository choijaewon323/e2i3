package com.example.e2i3.dto;

import com.example.e2i3.entity.Board;
import com.example.e2i3.entity.Comment;
import lombok.*;

@AllArgsConstructor
@Setter
@Getter
@NoArgsConstructor
public class CommentDTO {
    private Long id;
    private String writer;
    private String content;
    private Long board_id;

    public Comment toEntity(Board board) {
        return Comment.builder()
                .writer(writer)
                .content(content)
                .board(board)
                .build();
    }
}
