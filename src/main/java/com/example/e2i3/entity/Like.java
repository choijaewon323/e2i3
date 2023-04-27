package com.example.e2i3.entity;

import com.example.e2i3.dto.BoardDTO;
import com.example.e2i3.dto.LikeDTO;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
@Entity
@Table(name = "LOVE")
public class Like {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "LOVE_ID")
    private Long id;

    @ManyToOne
    private Board board;

    @ManyToOne
    private Member member;

    @Builder
    public Like(Board board, Member member) {
        this.board = board;
        this.member = member;
    }

}
