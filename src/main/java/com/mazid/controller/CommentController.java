package com.mazid.controller;

import com.mazid.models.Comment;
import com.mazid.models.User;
import com.mazid.service.CommentService;
import com.mazid.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class CommentController {
    @Autowired
    private CommentService commentService;

    @Autowired
    private UserService userService;

    // Endpoint to create a new comment on a post
    @PostMapping("/api/comments/post/{postId}")
    public Comment createComment(@RequestBody Comment comment,
                                 @RequestHeader("Authorization") String jwt,
                                 @PathVariable("postId") Integer postId
    ) throws Exception {

        // Find the user from the JWT token
        User user = userService.findUserByJwt(jwt);

        // Create the comment for the specified post
        Comment createdComment = commentService.createComment(comment, postId, user.getId());

        return createdComment;
    }

    // Endpoint to like a comment
    @PutMapping("/api/comments/like/{commentId}")
    public Comment likeComment(@RequestHeader("Authorization") String jwt,
                               @PathVariable("commentId") Integer commentId
    ) throws Exception {

        // Find the user from the JWT token
        User user = userService.findUserByJwt(jwt);

        // Like the specified comment
        Comment likeComment = commentService.likeComment(commentId, user.getId());

        return likeComment;
    }
}
