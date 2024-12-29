package com.mazid.service;

import com.mazid.models.Message;
import com.mazid.models.User;
import java.util.List;

public interface MessageService {

    // Method to create a new message in a specific chat.
    // Takes a User, chatId, and the Message object to create a new message.
    // Throws an exception if any error occurs while creating the message.
    public Message createMessage(User user, Integer chatId, Message req) throws Exception;

    // Method to find all messages for a specific chat by chatId.
    // Returns a list of all messages associated with the given chatId.
    public List<Message> findChatsMessages(Integer chatId) throws Exception;
}
