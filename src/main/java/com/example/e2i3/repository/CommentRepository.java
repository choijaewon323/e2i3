package com.example.e2i3.repository;

import com.example.e2i3.entity.Board;
import com.example.e2i3.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByBoard(Board board);
}
