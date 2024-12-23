package com.mazid.models;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

// @Entity annotation indicates that this class is an entity in the database.

@Entity
public class Post {
    // @Id annotation indicates the primary key of the entity.
    @Id
    // @GeneratedValue indicates that the value of this field will be generated automatically.
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private  String caption;// Post caption, which is a short description or text for the post.
    private String image;// Post image URL or path to the image stored in the system.
    private String video;// Video URL or path to the video file associated with the post.
    @ManyToOne// @ManyToOne indicates that a post is associated with a single user (many posts can belong to one user).
    private User user;
    // @OneToMany indicates a one-to-many relationship with User entities for users who liked the post.
    @OneToMany// -------api error-----------// - This might be an issue, as it should reference a liking entity.
    private List<User> liked = new ArrayList<>();
    private LocalDateTime createdAt;// Timestamp for when the post was created.

    @OneToMany// One-to-many relationship with Comment entity; a post can have multiple comments.
    private List<Comment> comments = new ArrayList<>();

    // Default constructor
    public Post(){}
    // Constructor with parameters for initializing Post object.
    public Post(Integer id, String caption, String image, String video, User user, List<User> liked, LocalDateTime createdAt, List<Comment> comments) {
        super();
        this.id = id;
        this.caption = caption;
        this.image = image;
        this.video = video;
        this.user = user;
        this.liked = liked;
        this.createdAt = createdAt;
        this.comments = comments;
    }
    // Getters and Setters for each field.
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
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

    @OneToMany
    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }
}
