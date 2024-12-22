package com.mazid.controller;

import com.mazid.config.JwtProvider;
import com.mazid.models.User;
import com.mazid.repository.UserRepository;
import com.mazid.request.LoginRequest;
import com.mazid.response.AuthResponse;
import com.mazid.service.CustomerUserDetailsService;
import com.mazid.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private CustomerUserDetailsService customerUserDetails;

    // Endpoint for user registration
    @PostMapping("/signup")
    public AuthResponse createUser(@RequestBody User user) throws Exception {

        // Check if the user already exists with the given email
        User isExist = userRepository.findByEmail((user.getEmail()));

        if(isExist != null){
            throw new Exception("This email already used with another account");
        }

        // Create a new user object and set its properties
        User newUser = new User();
        newUser.setEmail(user.getEmail());
        newUser.setFirstName(user.getFirstName());
        newUser.setLastName(user.getLastName());
        newUser.setPassword(passwordEncoder.encode(user.getPassword()));

        // Save the new user in the database
        User savedUser = userRepository.save(newUser);

        // Authenticate the user by creating an authentication token
        Authentication authentication = new UsernamePasswordAuthenticationToken(savedUser.getEmail(),savedUser.getPassword());

        // Generate JWT token
        String token = JwtProvider.generateToken(authentication);

        // Return a response with the token and success message
        AuthResponse res = new AuthResponse(token,"Register Successful");

        return res;
    }

    // Endpoint for user login
    @PostMapping("/signin")
    public AuthResponse signin(@RequestBody LoginRequest loginRequest){

        // Authenticate the user with the provided email and password
        Authentication authentication = authenticate(loginRequest.getEmail(),loginRequest.getPassword());

        // Generate JWT token upon successful authentication
        String token = JwtProvider.generateToken(authentication);

        // Return the token with a success message
        AuthResponse res = new AuthResponse(token,"Login Success");
        return res;
    }

    // Helper method to authenticate a user by email and password
    private Authentication authenticate(String email, String password) {

        // Load the user details using the email
        UserDetails userDetails = customerUserDetails.loadUserByUsername(email);

        // Check if the user exists
        if(userDetails == null){
            throw new BadCredentialsException("invalid user");
        }

        // Check if the password matches
        if(!passwordEncoder.matches(password,userDetails.getPassword())){
            throw new BadCredentialsException("wrong password");
        }

        // Return the authentication token if authentication is successful
        return new UsernamePasswordAuthenticationToken(userDetails,null,
                userDetails.getAuthorities());
    }
}
