package com.mazid.service;

import com.mazid.models.Post;
import java.util.List;

public interface PostService {

    // Method to create a new post.
    // Takes a Post object and userId to associate the post with a specific user.
    // Throws an exception if the post creation fails.
    public Post createNewPost(Post post, Integer userId) throws Exception;

    // Method to delete a post by its ID.
    // Takes postId and userId to ensure that the user is authorized to delete the post.
    // Returns a confirmation message or throws an exception if the deletion fails.
    public String deletePost(Integer postId, Integer userId) throws Exception;

    // Method to find all posts by a specific user.
    // Takes userId to return a list of posts created by the user.
    public List<Post> findPostByUserId(Integer userId);

    // Method to find a post by its ID.
    // Takes postId and returns the Post object or throws an exception if not found.
    public Post findPostById(Integer postId) throws Exception;

    // Method to find all posts in the system.
    // Returns a list of all posts available.
    public List<Post> findAllPost();

    // Method to save a post (likely adding to a list of saved posts for the user).
    // Takes postId and userId to mark the post as saved for a specific user.
    // Throws an exception if saving the post fails.
    public Post savedPost(Integer postId, Integer userId) throws Exception;

    // Method to "like" a post.
    // Takes postId and userId to add a like from a specific user to the post.
    // Throws an exception if there is an issue with liking the post.
    public Post likePost(Integer postId, Integer userId) throws Exception;
}
