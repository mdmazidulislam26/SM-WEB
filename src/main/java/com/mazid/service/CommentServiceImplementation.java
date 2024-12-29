package com.mazid.service;

import com.mazid.models.Comment;
import com.mazid.models.Post;
import com.mazid.models.User;
import com.mazid.repository.CommentRepository;
import com.mazid.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class CommentServiceImplementation implements CommentService {

    // Injecting necessary dependencies
    @Autowired
    private PostService postService;
    @Autowired
    private UserService userService;
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private PostRepository postRepository;

    // Method to create a new comment on a post
    @Override
    public Comment createComment(Comment comment, Integer postId, Integer userId) throws Exception {
        // Find user and post by their IDs
        User user = userService.findUserById(userId);
        Post post = postService.findPostById(postId);

        // Set the user and content of the comment
        comment.setUser(user);
        comment.setContent(comment.getContent());  // This seems redundant, could be removed
        comment.setCreatedAt(LocalDateTime.now());

        // Save the comment
        Comment savedComment = commentRepository.save(comment);

        // Add the saved comment to the post's comments
        post.getComments().add(savedComment);

        // Save the updated post with the new comment
        postRepository.save(post);

        // Return the saved comment
        return savedComment;
    }

    // Method to find a comment by its ID
    @Override
    public Comment findCommentById(Integer commentId) throws Exception {
        Optional<Comment> opt = commentRepository.findById(commentId);

        // If comment not found, throw an exception
        if (opt.isEmpty()) {
            throw new Exception("Comment not exist!");
        }

        // Return the found comment
        return opt.get();
    }

    // Method to like or unlike a comment
    @Override
    public Comment likeComment(Integer commentId, Integer userId) throws Exception {
        // Find the comment and user by their IDs
        Comment comment = findCommentById(commentId);
        User user = userService.findUserById(userId);

        // If user has not liked the comment, add them to the liked list, else remove them
        if (!comment.getLiked().contains(user)) {
            comment.getLiked().add(user);
        } else {
            comment.getLiked().remove(user);
        }

        // Save the updated comment
        return commentRepository.save(comment);
    }
}
