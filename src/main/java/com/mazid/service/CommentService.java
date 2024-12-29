package com.mazid.service;

import com.mazid.models.Comment;

public interface CommentService {

    // Method to create a new comment for a post.
    // Takes a Comment object, postId, and userId to associate the comment with a specific post and user.
    public Comment createComment(Comment comment, Integer postId, Integer userId) throws Exception;

    // Method to find a comment by its ID.
    // Returns the comment associated with the provided commentId or throws an exception if not found.
    public Comment findCommentById(Integer commentId) throws Exception;

    // Method to "like" a comment by a user.
    // Takes the commentId and userId to associate the like with the comment and the user.
    public Comment likeComment(Integer commentId, Integer userId) throws Exception;
}
