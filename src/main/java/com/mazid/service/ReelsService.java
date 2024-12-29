package com.mazid.service;

import com.mazid.models.Reels;
import com.mazid.models.User;
import java.util.List;

public interface ReelsService {

    // Method to create a new reel.
    // Takes a Reels object and a User object to associate the reel with a specific user.
    public Reels createReel(Reels reel, User user);

    // Method to find all reels in the system.
    // Returns a list of all reels available.
    public List<Reels> findAllReels();

    // Method to find all reels of a specific user.
    // Takes userId and returns a list of reels created by that user.
    // Throws an exception if the user's reels cannot be retrieved.
    public List<Reels> findUsersReel(Integer userId) throws Exception;
}
