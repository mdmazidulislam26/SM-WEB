package com.mazid.service;

import com.mazid.models.Chat;
import com.mazid.models.User;
import com.mazid.repository.ChatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ChatServiceImplementation implements ChatService {

    // Injecting the ChatRepository dependency to interact with the database
    @Autowired
    private ChatRepository chatRepository;

    // Method to create a new chat between two users
    // It first checks if the chat already exists between the two users
    @Override
    public Chat createChat(User reqUser, User user2) {
        // Check if chat already exists between the two users
        Chat isExist = chatRepository.findChatByUsersId(user2, reqUser);

        // If the chat already exists, return the existing chat
        if (isExist != null) {
            return isExist;
        }

        // If no chat exists, create a new one
        Chat chat = new Chat();
        chat.getUsers().add(user2);  // Adding the second user to the chat
        chat.getUsers().add(reqUser); // Adding the requesting user to the chat
        chat.setTimestamp(LocalDateTime.now());  // Set the timestamp to the current time

        // Save the chat to the database and return the saved chat
        return chatRepository.save(chat);
    }

    // Method to find a chat by its ID
    // Throws an exception if the chat is not found
    @Override
    public Chat findChatById(Integer chatId) throws Exception {
        Optional<Chat> opt = chatRepository.findById(chatId);

        // If chat is not found, throw an exception
        if (opt.isEmpty()) {
            throw new Exception("Chat not found with this id: " + chatId);
        }

        // Return the chat if found
        return opt.get();
    }

    // Method to find all chats associated with a specific user by their ID
    @Override
    public List<Chat> findUsersChat(Integer userId) {
        // Return a list of chats for the specified user ID
        return chatRepository.findByUsersId(userId);
    }
}
