package com.example.e2i3.controller;

import com.example.e2i3.dto.BoardDTO;
import com.example.e2i3.entity.Board;
import com.example.e2i3.service.BoardService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class BoardApiController {
    private final BoardService boardService;

    // 글 작성
    @PostMapping("/api/board")
    public Integer write(BoardDTO boardDTO)
    {
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

    // 2023 04 25
    // board detail 1
    @GetMapping("/api/board/detail")
    public BoardDTO detail(BoardDTO boardDTO){
        return boardService.detail(boardDTO);
        // 게시글 상세보기, 객체를 return 하도록함.
    }
    // board detail 2
    // board의 id
    @GetMapping("/api/board/detail/{id}")
    public BoardDTO detail2(@PathVariable Long id){
        return boardService.findById(id);
        // 게시글 상세보기, 객체를 return 하도록함.
    }


    // board list 1
    @GetMapping("/api/board/list")
    public List<BoardDTO> list(){
        return boardService.list();
        // 객체 list를 return 하도록?
    }
}
