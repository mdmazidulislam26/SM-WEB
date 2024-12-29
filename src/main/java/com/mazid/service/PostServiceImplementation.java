package com.mazid.service;

import com.mazid.models.Post;
import com.mazid.models.User;
import com.mazid.repository.PostRepository;
import com.mazid.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class PostServiceImplementation implements PostService {

    @Autowired
    PostRepository postRepository;

    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;

    @Override
    public Post createNewPost(Post post, Integer userId) throws Exception {
        // Find the user for the post
        User user = userService.findUserById(userId);

        // Create and save the new post
        Post newPost = new Post();
        newPost.setId(post.getId());
        newPost.setCaption(post.getCaption());
        newPost.setImage(post.getImage());
        newPost.setCreatedAt(LocalDateTime.now());
        newPost.setVideo(post.getVideo());
        newPost.setUser(user);

        return postRepository.save(newPost);
    }

    @Override
    public String deletePost(Integer postId, Integer userId) throws Exception {
        // Retrieve the post and user
        Post post = findPostById(postId);
        User user = userService.findUserById(userId);

        // Check if the user is the owner of the post
        if(post.getUser().getId() != user.getId()){
            throw new Exception("You can't delete another user's post");
        }

        // Remove the post from users who saved it
        for (User u : userRepository.findAll()) {
            if (u.getSavedPost().contains(post)) {
                u.getSavedPost().remove(post);
                userRepository.save(u);
            }
        }

        // Delete the post
        postRepository.delete(post);
        return "Post deleted successfully!";
    }

    @Override
    public List<Post> findPostByUserId(Integer userId) {
        // Return all posts by the given user
        return postRepository.findPostByUserId(userId);
    }

    @Override
    public Post findPostById(Integer postId) throws Exception {
        // Fetch the post by ID
        Optional<Post> opt = postRepository.findById(postId);
        if(opt.isEmpty()){
            throw new Exception("Post not found with this Id: " + postId);
        }
        return opt.get();
    }

    @Override
    public List<Post> findAllPost() {
        // Return all posts
        return postRepository.findAll();
    }

    @Override
    public Post savedPost(Integer postId, Integer userId) throws Exception {
        // Toggle the saved status of the post for the user
        Post post = findPostById(postId);
        User user = userService.findUserById(userId);

        if(user.getSavedPost().contains(post)){
            user.getSavedPost().remove(post);
        } else {
            user.getSavedPost().add(post);
        }

        // Save the updated user
        userRepository.save(user);
        return post;
    }

    @Override
    public Post likePost(Integer postId, Integer userId) throws Exception {
        // Toggle the like status of the post for the user
        Post post = findPostById(postId);
        User user = userService.findUserById(userId);

        if(post.getLiked().contains(user)){
            post.getLiked().remove(user);
        } else {
            post.getLiked().add(user);
        }

        // Save the updated post
        return postRepository.save(post);
    }
}
