package com.mazid.models;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

// @Entity annotation indicates that this class represents a database entity.
@Entity
public class Comment {
    @Id // @Id annotation marks the primary key of the entity.
    @GeneratedValue(strategy = GenerationType.AUTO)// @GeneratedValue strategy indicates that the id field will be auto-generated.
    private Integer id;
    // Content of the comment, which is the text written by the user.
    private String content;
    @ManyToOne// @ManyToOne indicates that each comment is associated with a single user (many comments can belong to one user).
    private User user;
    // @ManyToMany indicates a many-to-many relationship between comments and users who liked the comment.
    // Multiple users can like a single comment.
    @ManyToMany
    private List<User> liked = new ArrayList<>();
    private LocalDateTime createdAt;    // Timestamp for when the comment was created.

    public Comment(){}// Default constructor
    // Parameterized constructor for initializing a Comment object.
    public Comment(Integer id, String content, User user, List<User> liked, LocalDateTime createdAt) {
        super();
        this.id = id;
        this.content = content;
        this.user = user;
        this.liked = liked;
        this.createdAt = createdAt;
    }
    // Getter and Setter methods for each field.
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<User> getLiked() {
        return liked;
    }

    public void setLiked(List<User> liked) {
        this.liked = liked;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
