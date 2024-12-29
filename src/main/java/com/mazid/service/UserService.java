package com.mazid.service;

import com.mazid.models.User;
import java.util.List;

public interface UserService {

    // Method to register a new user.
    // Takes a User object as input and returns the newly registered User object.
    public User registerUser(User user);

    // Method to find a user by their userId.
    // Takes a userId as input and returns the corresponding User object.
    // Throws an exception if the user is not found.
    User findUserById(Integer userId) throws Exception;

    // Method to find a user by their email.
    // Takes an email string as input and returns the corresponding User object.
    public User findUserByEmail(String email);

    // Method for a user to follow another user.
    // Takes two userIds (the follower and the followed user) and returns the updated User object.
    // Throws an exception if the user cannot follow another user (e.g., if they are already following).
    public User followUser(Integer userId1, Integer userId2) throws Exception;

    // Method to update a user's information.
    // Takes a User object with updated information and the userId to identify which user to update.
    // Throws an exception if the user is not found or the update fails.
    public User updateUser(User user, Integer userId) throws Exception;

    // Method to search users based on a query string.
    // Returns a list of users whose first name, last name, or email contains the query string.
    public List<User> searchUser(String query);

    // Method to find a user by their JWT (JSON Web Token).
    // Takes a JWT as input and returns the corresponding User object.
    public User findUserByJwt(String jwt);
}
