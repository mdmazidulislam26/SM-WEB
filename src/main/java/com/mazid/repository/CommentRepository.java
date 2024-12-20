package com.mazid.repository;

import com.mazid.models.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository
        extends JpaRepository<Comment,Integer> {
}
