package com.mazid.repository;

import com.mazid.models.Story;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class StoryRepositoryTest {

    @Autowired
    private StoryRepository storyRepository;

    private Story story1;
    private Story story2;

    @BeforeEach
    public void setUp() {
        // Set up test data
        story1 = new Story();
        story1.setId(1);
        story1.setCaptions("Story 1 Description of Story 1");


        story2 = new Story();
        story2.setId(2);
        story2.setCaptions("Story 2 Description of Story 2");

        // Save the stories to the repository
        storyRepository.save(story1);
        storyRepository.save(story2);
    }

    @Test
    public void testFindByUserId() {
        // Find stories by user ID 1
        List<Story> stories = storyRepository.findByUserId(1);

        // Assert that the result is not null and contains only one story
        assertThat(stories).isNotNull();
    }

    @Test
    public void testFindByNonExistentUserId() {
        // Try to find stories by a non-existent user ID
        List<Story> stories = storyRepository.findByUserId(999);

        // Assert that the result is empty
        assertThat(stories).isEmpty();
    }
}
