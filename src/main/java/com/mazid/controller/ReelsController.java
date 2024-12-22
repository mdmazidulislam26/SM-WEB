package com.mazid.controller;

import com.mazid.models.Story;
import com.mazid.models.User;
import com.mazid.service.StoryService;
import com.mazid.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StoryController {

    @Autowired
    private StoryService storyService;
    @Autowired
    private UserService userService;

    // Endpoint to create a new story for a user
    @PostMapping("/api/story")
    public Story createStory(@RequestBody Story story, @RequestHeader("Authorization") String jwt) {
        // Find the user from the JWT token
        User reqUser = userService.findUserByJwt(jwt);

        // Create a new story for the user
        Story createdStory = storyService.createStory(story, reqUser);

        return createdStory;
    }

    // Endpoint to get all stories of a specific user by userId
    @GetMapping("/api/story/user/{userId}")
    public List<Story> findUsersStory(@PathVariable Integer userId, @RequestHeader("Authorization") String jwt) throws Exception {
        // Find the user from the JWT token
        User reqUser = userService.findUserByJwt(jwt);

        // Retrieve all stories for the user by userId
        List<Story> stories = storyService.findStoryByUserId(userId);

        return stories;
    }
}
