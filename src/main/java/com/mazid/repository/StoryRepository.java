package com.mazid.repository;

import com.mazid.models.Story;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

// JpaRepository provides JPA related methods for working with Story entity.
public interface StoryRepository extends JpaRepository<Story, Integer> {

    // Method to find all stories by a specific user's ID.
    // This returns a list of stories associated with the given user.
    public List<Story> findByUserId(Integer userId);
}
