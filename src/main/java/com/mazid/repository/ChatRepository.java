package com.mazid.repository;

import com.mazid.models.Chat;
import com.mazid.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

// JpaRepository provides JPA related methods for working with Chat entity.
public interface ChatRepository extends JpaRepository<Chat, Integer> {

    // Method to find chats by a user's ID.
    // It retrieves all chats where the given user is a member.
    public List<Chat> findByUsersId(Integer userId);

    // Custom query to find a chat between two specific users.
    // The query checks if both users are members of the chat.
    @Query("SELECT c FROM Chat c WHERE :user MEMBER OF c.users AND :reqUser MEMBER OF c.users")
    public Chat findChatByUsersId(@Param("user") User user, @Param("reqUser") User reqUser);
}
