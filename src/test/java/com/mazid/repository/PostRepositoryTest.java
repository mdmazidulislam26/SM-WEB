package com.mazid.repository;

import com.mazid.models.Post;
import com.mazid.models.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class PostRepositoryTest {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;

    private User testUser;
    private Post testPost;

    @BeforeEach
    void setUp() {
        // Creating a test user
        testUser = new User();
        testUser.setFirstName("Alice");
        testUser.setLastName("Smith");
        testUser.setEmail("alice.smith@example.com");
        userRepository.save(testUser);  // Save the test user

        // Creating a post for the test user
        testPost = new Post();
        testPost.setCaption("Test Post");
//        testPost.setContent("This is a test post.");
        testPost.setUser(testUser); // Linking the post to the test user
        postRepository.save(testPost);  // Save the post
    }

    @Test
    void testFindPostByUserId() {
        List<Post> posts = postRepository.findPostByUserId(testUser.getId());
        assertThat(posts).isNotEmpty();
        assertThat(posts.get(0).getUser().getId()).isEqualTo(testUser.getId());
        assertThat(posts.get(0).getCaption()).isEqualTo("Test Post");
    }

    @Test
    void testFindPostByUserId_NoPosts() {
        // Creating another user without posts
        User newUser = new User();
        newUser.setFirstName("Bob");
        newUser.setLastName("Johnson");
        newUser.setEmail("bob.johnson@example.com");
        userRepository.save(newUser);

        List<Post> posts = postRepository.findPostByUserId(newUser.getId());
        assertThat(posts).isEmpty();  // No posts for this user
    }
}
