package com.mazid.models;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

// Entity class representing a User in the application, mapped to the "users" table in the database
@Entity
@Table(name = "users")
public class User {
    // Primary key for the User entity, auto-generated
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
    private String firstName;// User's first name
    private String lastName;// User's last name
    private String email;// User's email address (unique)
    private String password;// User's password
    private String gender;// User's gender
    private List<Integer> followers = new ArrayList<>();// List of followers (represented by their User IDs)
    private List<Integer> followings = new ArrayList<>();// List of followings (represented by their User IDs)

    // List of saved posts by the user (many-to-many relationship with Post entity)
    @JsonIgnore// Prevents this field from being serialized into JSON (avoids infinite recursion)
    @ManyToMany
    private List<Post> savedPost = new ArrayList<>();
    // Default constructor
    public User(){}
    // Constructor to initialize all fields
    public User(Integer id, String firstName, String lastName, String email, String password, String gender, List<Integer> followers, List<Integer> followings, List<Post> savedPost) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.gender = gender;
        this.followers = followers;
        this.followings = followings;
        this.savedPost = savedPost;
    }
    // Getter and setter methods for each field
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public List<Integer> getFollowers() {
        return followers;
    }

    public void setFollowers(List<Integer> followers) {
        this.followers = followers;
    }

    public List<Integer> getFollowings() {
        return followings;
    }

    public void setFollowings(List<Integer> followings) {
        this.followings = followings;
    }

    public List<Post> getSavedPost() {
        return savedPost;
    }

    public void setSavedPost(List<Post> savedPost) {
        this.savedPost = savedPost;
    }
}
