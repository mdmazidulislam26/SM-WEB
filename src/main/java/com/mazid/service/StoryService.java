package com.mazid.service;

import com.mazid.models.Story;
import com.mazid.models.User;
import java.util.List;

public interface StoryService {

    // Method to create a new story.
    // Takes a Story object and a User object to associate the story with a specific user.
    // Returns the created Story object.
    public Story createStory(Story story, User user);

    // Method to find all stories by a specific user.
    // Takes userId and returns a list of stories created by that user.
    // Throws an exception if the user's stories cannot be retrieved.
    public List<Story> findStoryByUserId(Integer userId) throws Exception;
}
