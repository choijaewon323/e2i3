package com.example.e2i3.service;

import com.example.e2i3.dto.BoardDTO;
import com.example.e2i3.entity.Board;
import com.example.e2i3.entity.Comment;
import com.example.e2i3.entity.Heart;
import com.example.e2i3.repository.BoardRepository;
import com.example.e2i3.repository.CommentRepository;
import com.example.e2i3.repository.HeartRepository;
import com.example.e2i3.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class BoardService {
    private final BoardRepository boardRepository;
    private final CommentRepository commentRepository;
    private final HeartRepository heartRepository;
    // 04 25
    private final MemberRepository memberRepository;

    @Transactional
    public Integer write(BoardDTO boardDTO) {
        // 추가 예외처리 필요
        if(boardDTO.getTitle().isEmpty() || boardDTO.getContent().isEmpty() || boardDTO.getWriter().isEmpty()){
            return 0;
        }
        else{
            boardRepository.save(boardDTO.toEntity());
            return 1;
        }
    }

    @Transactional
    public Integer deleteById(Long id) {
        Optional<Board> byId = boardRepository.findById(id);

        if(byId.isPresent()){
            Board board = byId.get();

            // comment 지우기
            List<Comment> findComment = commentRepository.findByBoard(board);
            for (Comment comment : findComment) {
                commentRepository.deleteById(comment.getId());
            }

            // like 지우기
            List<Heart> byBoard = heartRepository.findByBoard(board);
            for(Heart heart : byBoard){
                heartRepository.deleteById(heart.getId());
            }

            // board 삭제
            boardRepository.deleteById(id);
            return 1;
        }else{
            return 0;
        }
    }

    @Transactional
    public void update(Long id, BoardDTO boardDTO) {
        // 본인만 수정 예외처리 필요

        Board board = boardRepository.findById(id).orElseThrow();
        board.update(boardDTO);
    }

    // board detail
    public BoardDTO findById(Long id) {

        Optional<Board> byId = boardRepository.findById(id);

        if(byId.isPresent()){
            BoardDTO boardDTO = byId.get().toBoardDTO();
            return boardDTO;
        }else{
            return null;
        }
    }

    // board list
    public List<BoardDTO> list() {
        List<Board>boardList = boardRepository.findAllByOrderByIdDesc();
        List<BoardDTO>boardDTOList = new ArrayList<>();

        // entity to dto list
        for(Board board : boardList){
            boardDTOList.add(board.toBoardDTO());
        }

        return boardDTOList;
    }



}
