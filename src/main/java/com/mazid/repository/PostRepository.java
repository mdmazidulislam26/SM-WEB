package com.mazid.repository;

import com.mazid.models.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

// JpaRepository provides JPA related methods for working with Post entity.
public interface PostRepository extends JpaRepository<Post, Integer> {

    // Custom query to find posts by the user's ID.
    // This query retrieves all posts created by a specific user.
    @Query("SELECT p FROM Post p WHERE p.user.id = :userId")
    List<Post> findPostByUserId(Integer userId);
}
