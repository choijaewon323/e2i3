package com.example.e2i3.dto;


import com.example.e2i3.entity.Board;
import com.example.e2i3.entity.Comment;
import com.example.e2i3.entity.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class BoardDTO {

    private Long id;
    private String title;
    private String content;
    private String writer;
    private Long likeCnt;

    // 04 26
    //private List<Comment> commentList;


    @Builder
    public BoardDTO(Long id, String title, String content, String writer, Long likeCnt) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.writer = writer;
        this.likeCnt = likeCnt;
    }

    @Builder
    public BoardDTO(String title, String content, String writer, Long likeCnt) {
        this.title = title;
        this.content = content;
        this.writer = writer;
        this.likeCnt = likeCnt;
    }


    public Board toEntity(){
        return Board.builder().title(title).writer(writer).content(content).build();
    }

}
