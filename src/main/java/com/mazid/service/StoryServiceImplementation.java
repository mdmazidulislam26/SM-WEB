package com.mazid.service;

import com.mazid.models.Story;
import com.mazid.models.User;
import com.mazid.repository.StoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class StoryServiceImplementation implements StoryService {

    @Autowired
    private StoryRepository storyRepository;

    @Autowired
    private UserService userService;

    @Override
    public Story createStory(Story story, User user) {
        // Create and set up the new story
        Story createStory = new Story();

        createStory.setCaptions(story.getCaptions());
        createStory.setImage(story.getImage());
        createStory.setUser(user);
        createStory.setTimeStamp(LocalDateTime.now());

        // Save the new story and return
        return storyRepository.save(createStory);
    }

    @Override
    public List<Story> findStoryByUserId(Integer userId) throws Exception {
        // Verify the user exists before retrieving their stories
        User user = userService.findUserById(userId);

        // Return all stories by the specified user
        return storyRepository.findByUserId(userId);
    }
}
