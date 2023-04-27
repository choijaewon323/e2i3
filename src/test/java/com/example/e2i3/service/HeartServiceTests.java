package com.example.e2i3.service;

import com.example.e2i3.entity.Board;
import com.example.e2i3.entity.Heart;
import com.example.e2i3.entity.Member;
import com.example.e2i3.repository.BoardRepository;
import com.example.e2i3.repository.HeartRepository;
import com.example.e2i3.repository.MemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class HeartServiceTests {
    @Autowired
    HeartService heartService;
    @Autowired
    HeartRepository heartRepository;
    @Autowired
    BoardRepository boardRepository;
    @Autowired
    MemberRepository memberRepository;

    @AfterEach
    void afterEach() {
        // 매핑 관계 때문에 heart를 먼저 없애고 나머지를 지워야함
        heartRepository.deleteAll();
        boardRepository.deleteAll();
        memberRepository.deleteAll();
    }

    @Test
    void getHeartTest() {
        Board board = Board.builder()
                .title("제목")
                .content("내용")
                .writer("작성자")
                .build();
        Board board1 = Board.builder()
                .title("제목1")
                .content("내용1")
                .writer("작성자1")
                .build();
        Member member = Member.builder()
                .email("아이디")
                .password("비번")
                .build();

        boardRepository.save(board);
        boardRepository.save(board1);
        memberRepository.save(member);

        // test
        Heart heart = Heart.builder()
                .board(board)
                .member(member)
                .build();

        heartRepository.save(heart);

        Integer mustOne = heartService.getHeart(board.getId(), member.getEmail());
        Integer mustTwo = heartService.getHeart(board1.getId(), member.getEmail());

        assertThat(mustOne).isEqualTo(1);
        assertThat(mustTwo).isEqualTo(0);
    }

    @Test
    void pushHeartTest() {
        Board board = boardRepository.save(
                Board.builder()
                .title("제목")
                .content("내용")
                .writer("작성자")
                .build());
        Member member = memberRepository.save(
                Member.builder()
                .email("아이디")
                .password("비번")
                .build());

        // test
        Integer mustOne = heartService.pushHeart(board.getId(), member.getEmail());
        Integer mustZero = heartService.pushHeart(board.getId(), member.getEmail());

        assertThat(mustOne).isEqualTo(1);
        assertThat(mustZero).isEqualTo(0);

        Board resultBoard = boardRepository.findById(board.getId())
                .orElseThrow();

        assertThat(resultBoard.getHeartCnt()).isEqualTo(1L);
    }

    @Test
    void popHeartTest() {
        Board board = Board.builder()
                .title("제목")
                .content("내용")
                .writer("작성자")
                .build();
        Member member = Member.builder()
                .email("아이디")
                .password("비번")
                .build();

        boardRepository.save(board);
        memberRepository.save(member);

        // test
        heartService.pushHeart(board.getId(), member.getEmail());
        Integer mustOne = heartService.popHeart(board.getId(), member.getEmail());
        Integer mustZero = heartService.popHeart(board.getId(), member.getEmail());

        assertThat(mustOne).isEqualTo(1);
        assertThat(mustZero).isEqualTo(0);

        Board resultBoard = boardRepository.findById(board.getId())
                .orElseThrow();

        assertThat(resultBoard.getHeartCnt()).isEqualTo(0L);
    }
}
