package com.mazid.service;

import com.mazid.models.Chat;
import com.mazid.models.User;
import java.util.List;

// Service interface for handling operations related to the Chat entity.
public interface ChatService {

    // Method to create a chat between two users.
    // This method takes two User objects and creates a chat between them.
    public Chat createChat(User reqUser, User user2);

    // Method to find a chat by its ID.
    // This method returns a Chat object for a given chat ID,
    // or throws an exception if the chat is not found.
    public Chat findChatById(Integer chatId) throws Exception;

    // Method to find all chats associated with a specific user.
    // This method returns a list of all chats for a user, given the user's ID.
    public List<Chat> findUsersChat(Integer userId);
}
