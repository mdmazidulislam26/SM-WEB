package com.mazid.service;

import com.mazid.models.Reels;
import com.mazid.models.User;
import com.mazid.repository.ReelsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReelsServiceImplementation implements ReelsService {

    @Autowired
    private ReelsRepository reelsRepository;

    @Autowired
    private UserService userService;

    @Override
    public Reels createReel(Reels reel, User user) {
        // Create a new reel and set its properties
        Reels createReel = new Reels();

        createReel.setTitle(reel.getTitle());
        createReel.setUser(user);
        createReel.setVideo(reel.getVideo());

        // Save the new reel to the repository
        return reelsRepository.save(createReel);
    }

    @Override
    public List<Reels> findAllReels() {
        // Return all reels
        return reelsRepository.findAll();
    }

    @Override
    public List<Reels> findUsersReel(Integer userId) throws Exception {
        // Ensure the user exists before fetching their reels
        userService.findUserById(userId);

        // Return all reels for the given user
        return reelsRepository.findByUserId(userId);
    }
}
