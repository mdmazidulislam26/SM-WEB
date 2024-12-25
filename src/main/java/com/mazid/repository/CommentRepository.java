package com.mazid.repository;

import com.mazid.models.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

// JpaRepository provides JPA related methods for working with Comment entity.
public interface CommentRepository extends JpaRepository<Comment, Integer> {
    // JpaRepository automatically provides basic CRUD methods for the Comment entity.
    // These include methods such as save(), findById(), findAll(), deleteById(), etc.
    // No custom methods are needed here, but they can be added if required.
}
