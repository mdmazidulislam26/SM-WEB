package com.mazid.controller;

import com.mazid.models.Post;
import com.mazid.models.User;
import com.mazid.response.ApiResponse;
import com.mazid.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.mazid.service.PostService;

import java.util.List;

@RestController
public class PostController {

    @Autowired
    PostService postService;

    @Autowired
    UserService userService;

    // Endpoint to create a new post
    @PostMapping("/api/posts")
    public ResponseEntity<Post> createPost(@RequestHeader("Authorization")String jwt, @RequestBody Post post) throws Exception {
        User reqUser = userService.findUserByJwt(jwt);
        Post createdPost = postService.createNewPost(post, reqUser.getId());

        return new ResponseEntity<>(createdPost, HttpStatus.ACCEPTED);
    }

    // Endpoint to delete a post by its ID
    @DeleteMapping("/api/posts/{postId}")
    public ResponseEntity<ApiResponse> deletePost(@PathVariable Integer postId, @RequestHeader("Authorization")String jwt) throws Exception {
        User reqUser = userService.findUserByJwt(jwt);
        String message = postService.deletePost(postId, reqUser.getId());
        ApiResponse res = new ApiResponse(message, true);

        return new ResponseEntity<ApiResponse>(res, HttpStatus.OK);
    }

    // Endpoint to get a post by its ID
    @GetMapping("/api/posts/{postId}")
    public ResponseEntity<Post> findPostByIdHandler(@PathVariable Integer postId) throws Exception {
        Post post = postService.findPostById(postId);

        return new ResponseEntity<Post>(post, HttpStatus.ACCEPTED);
    }

    // Endpoint to get all posts of a specific user
    @GetMapping("/api/posts/user/{userId}")
    public ResponseEntity<List<Post>> findUsersPost(@PathVariable("userId") Integer userId) {
        List<Post> posts = postService.findPostByUserId(userId);

        return new ResponseEntity<List<Post>>(posts, HttpStatus.OK);
    }

    // Endpoint to get all posts
    @GetMapping("/api/posts")
    public ResponseEntity<List<Post>> findAllPost() {
        List<Post> posts = postService.findAllPost();

        return new ResponseEntity<List<Post>>(posts, HttpStatus.OK);
    }

    // Endpoint to save a post for a user
    @PutMapping("/api/posts/save/{postId}/user/{userId}")
    public ResponseEntity<Post> savedPostHandler(@PathVariable Integer postId, @RequestHeader("Authorization")String jwt) throws Exception {
        User reqUser = userService.findUserByJwt(jwt);
        Post post = postService.savedPost(postId, reqUser.getId());

        return new ResponseEntity<Post>(post, HttpStatus.ACCEPTED);
    }

    // Endpoint to like a post
    @PutMapping("/api/posts/like/{postId}")
    public ResponseEntity<Post> likePostHandler(@PathVariable Integer postId, @RequestHeader("Authorization")String jwt) throws Exception {
        User reqUser = userService.findUserByJwt(jwt);
        Post post = postService.likePost(postId, reqUser.getId());

        return new ResponseEntity<Post>(post, HttpStatus.ACCEPTED);
    }
}
