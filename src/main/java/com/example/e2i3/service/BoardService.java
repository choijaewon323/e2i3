package com.example.e2i3.service;

import com.example.e2i3.dto.BoardDTO;
import com.example.e2i3.dto.MemberDTO;
import com.example.e2i3.entity.Board;
import com.example.e2i3.entity.Member;
import com.example.e2i3.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.hibernate.type.SqlTypes.NULL;

@RequiredArgsConstructor
@Service
public class BoardService {
    private final BoardRepository boardRepository;

    @Transactional
    public Integer write(BoardDTO boardDTO) {
        // 하나라도 없는값이면 error
        if(boardDTO.getTitle().isEmpty() || boardDTO.getContent().isEmpty() || boardDTO.getWriter().isEmpty()){
            return 0;
        }
        else{
            boardRepository.save(boardDTO.toEntity());
            return 1;
        }
    }

    @Transactional
    public Integer deleteByTitle(BoardDTO boardDTO) {
        Optional<Board>byBoardTitle = boardRepository.findByTitle(boardDTO.getTitle());
        if(byBoardTitle.isPresent()){
            Board board = byBoardTitle.get();
            Long id = board.getId();
            String writer = board.getWriter();

            String writer1 = boardDTO.getWriter();

            if(writer.equals(writer1)){
                boardRepository.deleteById(id);
                return 1;
            }else{
                return 0;
            }
        }else {
            return 0;
        }
    }

    @Transactional
    public void update(BoardDTO boardDTO) {
        Board board = boardRepository.findById(boardDTO.getId()).orElseThrow();

        //

        board.update(boardDTO);
    }
}
