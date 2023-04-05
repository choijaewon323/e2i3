package com.example.e2i3.dto;


import com.example.e2i3.entity.Board;
import com.example.e2i3.entity.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BoardDTO {

    private Long id;
    private String title;
    private String content;
    private String writer;

    @Builder
    public BoardDTO(Long id, String title, String content, String writer) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.writer = writer;
    }

    @Builder
    public BoardDTO(String title, String content, String writer) {
        this.title = title;
        this.content = content;
        this.writer = writer;
    }


    public Board toEntity(){
        return Board.builder().title(title).writer(writer).content(content).build();
    }

}
