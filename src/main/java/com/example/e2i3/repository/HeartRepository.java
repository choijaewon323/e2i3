package com.example.e2i3.repository;

import com.example.e2i3.entity.Board;
import com.example.e2i3.entity.Heart;
import com.example.e2i3.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface HeartRepository extends JpaRepository<Heart, Long> {
    Optional<Heart> findByBoardAndMember(Board board, Member member);
}
