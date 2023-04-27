package com.example.e2i3.repository;

import com.example.e2i3.entity.Board;
import com.example.e2i3.entity.Like;
import com.example.e2i3.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LikeRepository extends JpaRepository<Like, Long> {
    Optional<Like> findByBoardAndMember(Board board, Member member);

    // 04 26
    List<Like> findByBoard(Board board);
    List<Like> findByMember(Member member);
    // 이게 들어가야되는데?

}
