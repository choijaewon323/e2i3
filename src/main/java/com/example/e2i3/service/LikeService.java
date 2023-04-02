package com.example.e2i3.service;

import com.example.e2i3.dto.LikeDTO;
import com.example.e2i3.entity.Board;
import com.example.e2i3.entity.Like;
import com.example.e2i3.entity.Member;
import com.example.e2i3.repository.BoardRepository;
import com.example.e2i3.repository.LikeRepository;
import com.example.e2i3.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;

@RequiredArgsConstructor
@Service
public class LikeService {
    private final LikeRepository likeRepository;
    private final BoardRepository boardRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public Integer pushLike(LikeDTO likeDTO) {
        Member findMember;
        Board findBoard;

        try {
            findMember = memberRepository.findById(likeDTO.getMemberID())
                    .orElseThrow(() -> new NoSuchElementException("Could not find such member"));

            findBoard = boardRepository.findById(likeDTO.getBoardID())
                    .orElseThrow(() -> new NoSuchElementException("Could not find such board"));

        } catch (Exception e) {
            e.printStackTrace();

            return 0;
        }

        Boolean present = likeRepository.findByBoardAndMember(findBoard, findMember).isPresent();

        // 이미 Like 엔티티가 존재 -> 이미 좋아요가 눌러진것
        if (present == true) {
            return 0;
        }

        Like like = Like.builder()
                .board(findBoard)
                .member(findMember)
                .build();

        likeRepository.save(like);
        findBoard.updateLike(true);
        return 1;
    }

    @Transactional
    public Integer popLike(LikeDTO likeDTO) {
        Board findBoard;
        Member findMember;
        Like like;

        try {
            findMember = memberRepository.findById(likeDTO.getMemberID())
                    .orElseThrow(() -> new NoSuchElementException("Could not find such member"));

            findBoard = boardRepository.findById(likeDTO.getBoardID())
                    .orElseThrow(() -> new NoSuchElementException("Could not find such board"));

            like = likeRepository.findByBoardAndMember(findBoard, findMember)
                    .orElseThrow(() -> new NoSuchElementException("Like does not pushed"));
        } catch (Exception e) {
            e.printStackTrace();

            return 0;
        }

        likeRepository.deleteById(like.getId());

        findBoard.updateLike(false);

        return 1;
    }
}
