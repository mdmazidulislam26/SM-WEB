package com.mazid.repository;

import com.mazid.models.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

//    private User testUser;
//
//    @BeforeEach
//    void setUp() {
//        testUser = new User();
//        testUser.setFirstName("John");
//        testUser.setLastName("Doe");
//        testUser.setEmail("john@gmail.com");
//        userRepository.save(testUser); // Save a test user to the database
//    }

    @Test
    void testFindByEmail() {
        User foundUser = userRepository.findByEmail("john@gmail.com");
        assertThat(foundUser).isNotNull();
        assertThat(foundUser.getEmail()).isEqualTo("john@gmail.com");
    }

    @Test
    void testSearchUser() {
        List<User> results = userRepository.searchUser("John");
        assertThat(results).isNotEmpty();
        assertThat(results.get(0).getFirstName()).isEqualTo("John");
    }

    @Test
    void testSearchUserNoMatch() {
        List<User> results = userRepository.searchUser("NonExistent");
        assertThat(results).isEmpty();
    }
}
