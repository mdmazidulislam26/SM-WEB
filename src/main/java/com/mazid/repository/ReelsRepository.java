package com.mazid.repository;

import com.mazid.models.Reels;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

// JpaRepository provides JPA related methods for working with Reels entity.
public interface ReelsRepository extends JpaRepository<Reels, Integer> {

    // Method to find all reels by a specific user's ID.
    // This returns a list of reels associated with the given user.
    public List<Reels> findByUserId(Integer userId);
}
