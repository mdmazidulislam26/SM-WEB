package com.mazid.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.mazid.models.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

// JpaRepository provides JPA related methods for working with User entity.
public interface UserRepository extends JpaRepository<User, Integer> {

    // Method to find a user by their email.
    // This is an automatically implemented method to find a user by their unique email address.
    public User findByEmail(String email);

    // Custom query method to search for users based on first name, last name, or email.
    // The query uses the LIKE operator to perform partial matches on firstName, lastName, and email fields.
    @Query("SELECT u FROM User u WHERE u.firstName LIKE %:query% OR u.lastName LIKE %:query% OR u.email LIKE %:query%")
    public List<User> searchUser(@Param("query") String query);
}
