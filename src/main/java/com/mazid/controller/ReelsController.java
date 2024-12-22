package com.mazid.controller;

import com.mazid.models.Reels;
import com.mazid.models.User;
import com.mazid.service.ReelsService;
import com.mazid.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ReelsController {

    @Autowired
    private ReelsService reelsService;

    @Autowired
    private UserService userService;

    // Endpoint to create a new reel for a user
    @PostMapping("/api/reels")
    public Reels createReels(@RequestBody Reels reel, @RequestHeader("Authorization") String jwt) {
        // Find the user from the JWT token
        User reqUser = userService.findUserByJwt(jwt);

        // Create a new reel for the user
        Reels createdReels = reelsService.createReel(reel, reqUser);

        return createdReels;
    }

    // Endpoint to get all reels
    @GetMapping("/api/reels")
    public List<Reels> findAllReels() {
        // Retrieve all reels
        List<Reels> reels = reelsService.findAllReels();

        return reels;
    }

    // Endpoint to get all reels for a specific user
    @GetMapping("/api/reels/user/{userId}")
    public List<Reels> findUserReels(@PathVariable Integer userId) throws Exception {
        // Retrieve all reels for the user by userId
        List<Reels> reels = reelsService.findUsersReel(userId);

        return reels;
    }
}
