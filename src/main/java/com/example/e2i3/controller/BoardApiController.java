package com.example.e2i3.controller;

import com.example.e2i3.dto.BoardDTO;
import com.example.e2i3.dto.MemberDTO;
import com.example.e2i3.entity.Board;
import com.example.e2i3.service.BoardService;
import com.example.e2i3.service.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class BoardApiController {
    private final BoardService boardService;

    // 글 작성
    @PostMapping("/api/board")
    public Integer write(BoardDTO boardDTO) {
        return boardService.write(boardDTO);
    }

    @DeleteMapping("/api/board")
    public Integer delete(BoardDTO boardDTO){
        return boardService.deleteByTitle(boardDTO);
    }

    @PutMapping("/api/board")
    public void updateBoard(BoardDTO boardDTO) {
        boardService.update(boardDTO);
    }
}
