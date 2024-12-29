package com.mazid.service;

import com.mazid.models.User;
import com.mazid.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerUserDetailsService implements UserDetailsService {

    // Injecting the UserRepository dependency to access user data from the database
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        // Attempt to retrieve the user by email (username)
        User user = userRepository.findByEmail(username);

        // Check if the user was found, if not throw an exception
        if (user == null) {
            // Logging the exception for debugging purposes could be useful
            throw new UsernameNotFoundException("User not found with this email: " + username);
        }

        // Creating a list of authorities (roles/permissions) for the user
        // For now, there are no roles assigned. We can add roles here if needed.
        List<GrantedAuthority> authorities = new ArrayList<>();

        // Returning a Spring Security User object with the user's email, password, and authorities
        // This object will be used by Spring Security to authenticate and authorize the user
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), authorities);
    }
}
