package com.example.e2i3.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
@Entity
public class Like {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "LIKE_ID")
    private Long id;

    @Column(name = "BOARD_ID")
    @ManyToOne
    private Board board;

    @Column(name = "MEMBER_ID")
    @ManyToOne
    private Member member;

    @Builder
    public Like(Board board, Member member) {
        this.board = board;
        this.member = member;
    }
}
