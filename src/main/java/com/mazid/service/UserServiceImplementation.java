package com.mazid.service;

import com.mazid.config.JwtProvider;
import com.mazid.models.User;
import com.mazid.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImplementation implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User registerUser(User user) {
        // Create a new user object and set its properties
        User newUser = new User();
        newUser.setEmail(user.getEmail());
        newUser.setFirstName(user.getFirstName());
        newUser.setLastName(user.getLastName());
        newUser.setPassword(user.getPassword());
        newUser.setId(user.getId());

        // Save the new user to the repository
        User savedUser = userRepository.save(newUser);
        return savedUser;
    }

    @Override
    public User findUserById(Integer userId) throws Exception {
        // Check if user exists by userId, if not, throw an exception
        Optional<User> user = userRepository.findById(userId);
        if(user.isPresent()){
            return user.get();
        }
        throw new Exception("User not exist with this user Id: "+userId);
    }

    @Override
    public User findUserByEmail(String email) {
        // Find and return the user by email
        return userRepository.findByEmail(email);
    }

    @Override
    public User followUser(Integer reqUserId, Integer userId2) throws Exception {
        // Retrieve users by their IDs and update their followers/followings
        User reqUser = findUserById(reqUserId);
        User user2 = findUserById(userId2);

        user2.getFollowers().add(reqUser.getId());
        reqUser.getFollowings().add(user2.getId());

        // Save the updated users
        userRepository.save(reqUser);
        userRepository.save(user2);

        return reqUser;
    }

    @Override
    public User updateUser(User user, Integer userId) throws Exception {
        // Find the user by userId, if not found, throw exception
        Optional<User> user1 = userRepository.findById(userId);
        if(user1.isEmpty()){
            throw new Exception("User not exist with this user Id: "+userId);
        }

        User oldUser = user1.get();

        // Update user properties if provided
        if(user.getFirstName() != null){
            oldUser.setFirstName(user.getFirstName());
        }
        if(user.getLastName() != null){
            oldUser.setLastName(user.getLastName());
        }
        if(user.getEmail() != null){
            oldUser.setEmail(user.getEmail());
        }
        if(user.getPassword() != null){
            oldUser.setPassword(user.getPassword());
        }
        if(user.getGender() != null){
            oldUser.setGender(user.getGender());
        }

        // Save the updated user
        return userRepository.save(oldUser);
    }

    @Override
    public List<User> searchUser(String query) {
        // Return a list of users matching the search query
        return userRepository.searchUser(query);
    }

    public User findUserByJwt(String jwt) {
        // Extract email from the JWT token and find the user by email
        String email = JwtProvider.getEmailFromJwtToken(jwt);
        return userRepository.findByEmail(email);
    }
}
