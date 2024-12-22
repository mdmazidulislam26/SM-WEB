package com.mazid.controller;

import com.mazid.models.User;
import com.mazid.repository.UserRepository;

import com.mazid.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

	@Autowired
	UserRepository userRepository;

	@Autowired
	UserService userService;

	// Endpoint to get a list of all users
	@GetMapping("/api/users")
	public List<User> getUsers() {
		List<User> users = userRepository.findAll();
		return users;
	}

	// Endpoint to get user details by userId
	@GetMapping("/api/users/{userId}")
	public User getUserById(@PathVariable("userId") Integer id) throws Exception {
		// Find user by ID
		User user = userService.findUserById(id);
		return user;
	}

	// Endpoint to update user details based on JWT token
	@PutMapping("/api/users")
	public User updateUser(@RequestHeader("Authorization") String jwt, @RequestBody User user) throws Exception {
		// Retrieve user from JWT token
		User reqUser = userService.findUserByJwt(jwt);
		// Update user details
		User updateUser = userService.updateUser(user, reqUser.getId());
		return updateUser;
	}

	// Endpoint to allow a user to follow another user
	@PutMapping("/api/users/follow/{userId2}")
	public User followUserHandler(@RequestHeader("Authorization") String jwt, @PathVariable Integer userId2) throws Exception {
		// Retrieve the user from JWT token
		User reqUser = userService.findUserByJwt(jwt);
		// Follow the specified user
		User user = userService.followUser(reqUser.getId(), userId2);
		return user;
	}

	// Endpoint to search users by a query
	@GetMapping("/api/users/search")
	public List<User> searchUser(@RequestParam("query") String query){
		List<User> users = userService.searchUser(query);
		return users;
	}

	// Endpoint to get the user profile from JWT token
	@GetMapping("/api/users/profile")
	public User getUserFromToken(@RequestHeader("Authorization") String jwt){
		// Find the user from the JWT token and remove password from response
		User user = userService.findUserByJwt(jwt);
		user.setPassword(null);
		return user;
	}

//    // Endpoint to delete a user (commented out)
//    @DeleteMapping("/api/users/{userId}")
//    public String deleteUser(@PathVariable("userId") Integer userId) throws Exception {
//
//        Optional<User> user = userRepository.findById(userId);
//        if(user.isEmpty()){
//            throw new Exception("User not exist with this user Id: "+userId);
//        }
//
//        userRepository.delete(user.get());
//        return "User ID is "+userId+" Delete Successfully!";
//    }
}
