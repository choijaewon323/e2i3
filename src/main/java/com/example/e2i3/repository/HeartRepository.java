package com.example.e2i3.repository;

import com.example.e2i3.entity.Board;
import com.example.e2i3.entity.Like;
import com.example.e2i3.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LikeRepository extends JpaRepository<Like, Long> {
    Optional<Like> findByBoardAndMember(Board board, Member member);


}
