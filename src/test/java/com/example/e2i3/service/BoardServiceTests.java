package com.example.e2i3.service;


import com.example.e2i3.controller.BoardApiController;
import com.example.e2i3.controller.LikeApiController;
import com.example.e2i3.controller.LoginApiController;
import com.example.e2i3.controller.MemberApiController;
import com.example.e2i3.dto.BoardDTO;
import com.example.e2i3.dto.LikeDTO;
import com.example.e2i3.dto.MemberDTO;
import com.example.e2i3.entity.Board;
import com.example.e2i3.entity.Like;
import com.example.e2i3.entity.Member;
import com.example.e2i3.repository.BoardRepository;
import com.example.e2i3.repository.LikeRepository;
import com.example.e2i3.repository.MemberRepository;
import com.example.e2i3.service.BoardService;
import jakarta.servlet.http.HttpServletRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

@SpringBootTest
class BoardServiceTests {

    @Autowired
    BoardApiController boardApiController;
    @Autowired
    MemberApiController memberApiController;
    @Autowired
    LoginApiController loginApiController;
    @Autowired
    LikeApiController likeApiController;
    @Autowired
    BoardService boardService;

    @Autowired
    BoardRepository boardRepository;
    @Autowired
    MemberRepository memberRepository;
    @Autowired
    private LikeRepository likeRepository;


    @Test
    void boardWrite(){
        BoardDTO boardDTO = BoardDTO.builder().writer("1").title("1").content("1").build();
        boardApiController.write(boardDTO);

        String title = boardRepository.findAll().get(0).getTitle();
        if(title.equals("1")){
            System.out.println("correct");
        }else{
            System.out.println("no");
        }
    }

    @Test
    void boardDetail2(){
        BoardDTO boardDTO = BoardDTO.builder().writer("1").title("1").content("1").build();
        boardApiController.write(boardDTO);

        Board board = boardRepository.findAll().get(0);
        // 얘는 아이디 가진다.

        boardDTO.setId(board.getId());

        BoardDTO boardDTO1 = boardApiController.detail2(boardDTO.getId());
        System.out.println(boardDTO1.getContent());
    }

    @Test
    void boardList(){
        BoardDTO boardDTO = BoardDTO.builder().writer("1").title("1").content("1").build();
        boardApiController.write(boardDTO);
        BoardDTO boardDTO2 = BoardDTO.builder().writer("2").title("2").content("2").build();
        boardApiController.write(boardDTO2);

        List<BoardDTO> list = boardApiController.list();
        System.out.println(list.get(0).getContent());
        System.out.println(list.get(1).getContent());

    }


    @Test
    void showLikeCnt(){
        BoardDTO boardDTO = BoardDTO.builder().writer("1").title("1").content("1").build();
        MemberDTO memberDTO = MemberDTO.builder().email("1").password("1").build();

        loginApiController.save(memberDTO);
        boardApiController.write(boardDTO);

        Board board = boardRepository.findAll().get(0);
        Member member = memberRepository.findAll().get(0);

        LikeDTO likeDTO = new LikeDTO(board.getId(),member.getId());
        likeApiController.pushLike(likeDTO);

        System.out.println(boardRepository.findAll().get(0).getLikeCnt());
        //System.out.println(likeApiController.pushLike(likeDTO));
        //likeApiController.popLike(likeDTO);
        //System.out.println(board.getLikeCnt());

        //board.updateLike(true);
        //System.out.println(board.getLikeCnt());
        // true 넣어주면 제대로 동작함 true 가 제대로 안넘어오나
        //board.updateLike(false);
        //System.out.println(board.getLikeCnt());
    }

}
