package com.example.e2i3.entity;

import com.example.e2i3.dto.BoardDTO;
import com.example.e2i3.dto.MemberDTO;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@Entity
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "BOARD_ID")
    private Long id;

    @Column(name = "TITLE")
    private String title;

    @Column(name = "CONTENT")
    private String content;

    @Column(name = "WRITER")
    private String writer;

    @Column(name = "HEARTCNT")
    private Long heartCnt = 0L;


    // 생성자
    @Builder
    public Board(String title, String content, String writer){
        this.title = title;
        this.content = content;
        this.writer = writer;
    }

    @Builder
    public Board(Long id, String title, String content, String writer){
        this.id = id;
        this.title = title;
        this.content = content;
        this.writer = writer;
    }

    public BoardDTO toBoardDTO(){
        return BoardDTO.builder()
                .id(id)
                .title(title)
                .content(content)
                .writer(writer)
                .build();
    }


    public void update(BoardDTO boardDTO){
        this.title = boardDTO.getTitle();
        this.content = boardDTO.getContent();
    }

    public void updateHeart(Boolean isPushed) {
        if (isPushed.equals(true)) {
            this.heartCnt++;
        } else {
            this.heartCnt--;
        }
    }
}
