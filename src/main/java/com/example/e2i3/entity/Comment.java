package com.example.e2i3.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "COMMENT_ID")
    private Long id;

    @Column(name = "WRITER")
    private String writer;

    @Column(name = "CONTENT")
    private String content;

    @ManyToOne
    private Board board;

    @Builder
    public Comment(String writer, String content, Board board) {
        this.writer = writer;
        this.content = content;
        this.board = board;
    }

    public void update(String writer, String content) {
        this.writer = writer;
        this.content = content;
    }
}
