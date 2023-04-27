package com.example.e2i3.service;

import com.example.e2i3.entity.Board;
import com.example.e2i3.entity.Heart;
import com.example.e2i3.entity.Member;
import com.example.e2i3.repository.BoardRepository;
import com.example.e2i3.repository.HeartRepository;
import com.example.e2i3.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;

@RequiredArgsConstructor
@Service
public class HeartService {
    private final HeartRepository heartRepository;
    private final BoardRepository boardRepository;
    private final MemberRepository memberRepository;

    public Integer getHeart(Long boardID, String email) {
        Board board = boardRepository.findById(boardID)
                .orElseThrow();
        Member member = memberRepository.findByEmail(email)
                .orElseThrow();
        Boolean isPushed = heartRepository.findByBoardAndMember(board, member).isPresent();

        if (isPushed.equals(true)) {
            return 1;
        } else {
            return 0;
        }
    }

    @Transactional
    public Integer pushHeart(Long boardID, String email) {
        Member findMember;
        Board findBoard;

        try {
            findMember = memberRepository.findByEmail(email)
                    .orElseThrow(() -> new NoSuchElementException("Could not find such member"));

            findBoard = boardRepository.findById(boardID)
                    .orElseThrow(() -> new NoSuchElementException("Could not find such board"));

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return 0;
        }

        Boolean present = heartRepository.findByBoardAndMember(findBoard, findMember).isPresent();

        // 이미 Like 엔티티가 존재 -> 이미 좋아요가 눌러진것
        if (present == true) {
            return 0;
        }

        Heart heart = Heart.builder()
                .board(findBoard)
                .member(findMember)
                .build();

        heartRepository.save(heart);
        findBoard.updateHeart(true);
        return 1;
    }

    @Transactional
    public Integer popHeart(Long boardID, String email) {
        Board findBoard;
        Member findMember;
        Heart heart;

        try {
            findMember = memberRepository.findByEmail(email)
                    .orElseThrow(() -> new NoSuchElementException("Could not find such member"));

            findBoard = boardRepository.findById(boardID)
                    .orElseThrow(() -> new NoSuchElementException("Could not find such board"));

            heart = heartRepository.findByBoardAndMember(findBoard, findMember)
                    .orElseThrow(() -> new NoSuchElementException("Like does not pushed"));

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return 0;
        }

        heartRepository.deleteById(heart.getId());
        findBoard.updateHeart(false);

        return 1;
    }
}
