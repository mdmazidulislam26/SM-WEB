package com.mazid.repository;

import com.mazid.models.Reels;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class ReelsRepositoryTest {

    @Autowired
    private ReelsRepository reelsRepository;

    private Reels reel1;
    private Reels reel2;

//    @BeforeEach
//    public void setUp() {
//        // Set up test data
//        reel1 = new Reels();
//        reel1.setId(1);
//        reel1.setTitle("Reel 1");
//        reel1.setTitle("Description of Reel 1");
//
//        reel2 = new Reels();
//        reel2.setId(2);
//        reel2.setTitle("Reel 2");
//        reel2.setTitle("Description of Reel 2");
//
//        // Save the reels to the repository
//        reelsRepository.save(reel1);
//        reelsRepository.save(reel2);
//    }

    @Test
    public void testFindByUserId() {
        // Find reels by user ID 1
        List<Reels> reels = reelsRepository.findByUserId(1);

        // Assert that the result is not null and contains only one reel
        assertThat(reels).isNotNull();
    }

    @Test
    public void testFindByNonExistentUserId() {
        // Try to find reels by a non-existent user ID
        List<Reels> reels = reelsRepository.findByUserId(999);

        // Assert that the result is empty
        assertThat(reels).isEmpty();
    }
}
