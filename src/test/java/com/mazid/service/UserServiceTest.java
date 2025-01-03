package com.mazid.service;

import com.mazid.models.User;
import com.mazid.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImplementation userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testRegisterUser() {
        // Arrange
        User user = new User();
        user.setEmail("test@example.com");
        user.setFirstName("John");
        user.setLastName("Doe");
        user.setPassword("password");

        when(userRepository.save(user)).thenReturn(user);

        // Act
        User registeredUser = user;

        // Assert
        assertNotNull(registeredUser);
        assertEquals("test@example.com", registeredUser.getEmail());
    }

    @Test
    void testFindUserById() throws Exception {
        // Arrange
        User user = new User();
        user.setId(1);
        user.setEmail("test@example.com");

        when(userRepository.findById(1)).thenReturn(Optional.of(user));

        // Act
        User foundUser = userService.findUserById(1);

        // Assert
        assertNotNull(foundUser);
        assertEquals(1, foundUser.getId());
        verify(userRepository, times(1)).findById(1);
    }

    @Test
    void testFindUserById_NotFound() {
        // Arrange
        when(userRepository.findById(1)).thenReturn(Optional.empty());

        // Act & Assert
        Exception exception = assertThrows(Exception.class, () -> userService.findUserById(1));
        assertEquals("User not exist with this user Id: 1", exception.getMessage());
        verify(userRepository, times(1)).findById(1);
    }

    @Test
    void testFindUserByEmail() {
        // Arrange
        User user = new User();
        user.setEmail("test@example.com");

        when(userRepository.findByEmail("test@example.com")).thenReturn(user);

        // Act
        User foundUser = userService.findUserByEmail("test@example.com");

        // Assert
        assertNotNull(foundUser);
        assertEquals("test@example.com", foundUser.getEmail());
        verify(userRepository, times(1)).findByEmail("test@example.com");
    }

    @Test
    void testFollowUser() throws Exception {
        // Arrange
        User user1 = new User();
        user1.setId(1);
        user1.setFollowings(new ArrayList<>());

        User user2 = new User();
        user2.setId(2);
        user2.setFollowers(new ArrayList<>());

        when(userRepository.findById(1)).thenReturn(Optional.of(user1));
        when(userRepository.findById(2)).thenReturn(Optional.of(user2));

        // Act
        User updatedUser = userService.followUser(1, 2);

        // Assert
        assertTrue(updatedUser.getFollowings().contains(2));
        verify(userRepository, times(2)).save(any(User.class));
    }

    @Test
    void testUpdateUser() throws Exception {
        // Arrange
        User existingUser = new User();
        existingUser.setId(1);
        existingUser.setFirstName("John");

        User updatedUser = new User();
        updatedUser.setFirstName("UpdatedName");

        when(userRepository.findById(1)).thenReturn(Optional.of(existingUser));
        when(userRepository.save(any(User.class))).thenAnswer(invocation -> invocation.getArgument(0));

        // Act
        User result = userService.updateUser(updatedUser, 1);

        // Assert
        assertNotNull(result);
        assertEquals("UpdatedName", result.getFirstName());
        verify(userRepository, times(1)).save(existingUser);
    }

    @Test
    void testSearchUser() {
        // Arrange
        List<User> users = new ArrayList<>();
        User user = new User();
        user.setEmail("test@example.com");
        users.add(user);

        when(userRepository.searchUser("test")).thenReturn(users);

        // Act
        List<User> result = userService.searchUser("test");

        // Assert
        assertNotNull(result);
        assertEquals(1, result.size());
        verify(userRepository, times(1)).searchUser("test");
    }
}
