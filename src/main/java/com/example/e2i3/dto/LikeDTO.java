package com.example.e2i3.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class LikeDTO {
    private Long boardID;
    private Long memberID;

    public LikeDTO(Long boardID, Long memberID) {
        this.boardID = boardID;
        this.memberID = memberID;
    }
}
