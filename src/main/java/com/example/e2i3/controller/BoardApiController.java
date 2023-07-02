package com.example.e2i3.controller;

import com.example.e2i3.dto.BoardDTO;
import com.example.e2i3.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class BoardApiController {
    private final BoardService boardService;

    // 글 작성
    @PostMapping("/api/board")
    public Integer write(@RequestBody BoardDTO boardDTO)
    {
        return boardService.write(boardDTO);
    }

    @DeleteMapping("/api/board/{id}")
    public Integer delete(@PathVariable Long id){
        return boardService.deleteById(id);
    }

    @PutMapping("/api/board/{id}")
    public void updateBoard(@PathVariable Long id, BoardDTO boardDTO) {
        boardService.update(id, boardDTO);
    }


    @GetMapping("/api/board/detail/{id}")
    public BoardDTO detail(@PathVariable Long id){
        return boardService.findById(id);
    }

    // board list 1
    @GetMapping("/api/board/list")
    public List<BoardDTO> list(){
        return boardService.list();
    }
}
