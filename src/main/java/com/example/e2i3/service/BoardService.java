package com.example.e2i3.service;

import com.example.e2i3.dto.BoardDTO;
import com.example.e2i3.entity.Board;
import com.example.e2i3.entity.Comment;
import com.example.e2i3.entity.Like;
import com.example.e2i3.repository.BoardRepository;
import com.example.e2i3.repository.CommentRepository;
import com.example.e2i3.repository.LikeRepository;
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

    private final LikeRepository likeRepository;
    // 04 25
    private final MemberRepository memberRepository;

    @Transactional
    public Integer write(BoardDTO boardDTO) {
        // 04 25
        // 예외처리 필요
        // member repository 뒤진다해도 이게 접속자라는 보장이 없음
        // 이렇게 예외처리하면 무조건 회원가입해야지 테스트 진행가능함
        if(memberRepository.findByEmail(boardDTO.getWriter()).isPresent()){
            if(boardDTO.getTitle().isEmpty() || boardDTO.getContent().isEmpty() || boardDTO.getWriter().isEmpty()){
                return 0;
            }
            else{
                boardRepository.save(boardDTO.toEntity());
                return 1;
            }
        }
        else{
            return 0;
        }
    }

    @Transactional
    public Integer deleteByTitle(BoardDTO boardDTO) {
        Optional<Board>byBoardTitle = boardRepository.findByTitle(boardDTO.getTitle());

        if (byBoardTitle.isPresent()) {
            Board board = byBoardTitle.get();
            Long id = board.getId();
            String writer = board.getWriter();

            String writer1 = boardDTO.getWriter();

            if (writer.equals(writer1)) {
                List<Comment> findComment = commentRepository.findByBoard(board);
                for (Comment comment : findComment) {
                    commentRepository.deleteById(comment.getId());
                }

                // 04 27
                List<Like> byBoard = likeRepository.findByBoard(board);
                for(Like like : byBoard){
                    likeRepository.deleteById(like.getId());
                }

                boardRepository.deleteById(id);
                return 1;
            } else {
                return 0;
            }
        } else {
            return 0;
        }
    }

    @Transactional
    public void update(BoardDTO boardDTO) {
        // 본인만 수정 예외처리

        Board board = boardRepository.findById(boardDTO.getId()).orElseThrow();

        board.update(boardDTO);
    }


    // 2023 04 25
    // board detail 1
    public BoardDTO detail(BoardDTO boardDTO) {
        // 예외처리 필요?
        //List<Comment> byBoard = commentRepository.findByBoard(boardDTO.toEntity());
        //boardDTO.setCommentList(byBoard);

        return boardRepository.findById(boardDTO.getId()).get().toBoardDTO();
        // 특별한 제약사항 없음
    }

    // board detail 2
    public BoardDTO findById(Long id) {

        Optional<Board> byId = boardRepository.findById(id);

        if(byId.isPresent()){
            BoardDTO boardDTO = byId.get().toBoardDTO();

            // 예외처리 필요?
            //List<Comment> byBoard = commentRepository.findByBoard(boardDTO.toEntity());
            //boardDTO.setCommentList(byBoard);
            return boardDTO;
        }else{
            return null;
        }
    }

    // board list 1
    public List<BoardDTO> list() {
        List<Board>boardList = boardRepository.findAll();
        List<BoardDTO>boardDTOList = new ArrayList<>();

        // entity to dto list
        for(Board board : boardList){
            boardDTOList.add(board.toBoardDTO());
        }

        return boardDTOList;
    }


}
