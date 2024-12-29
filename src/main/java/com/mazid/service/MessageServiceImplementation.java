package com.mazid.service;

import com.mazid.models.Chat;
import com.mazid.models.Message;
import com.mazid.models.User;
import com.mazid.repository.ChatRepository;
import com.mazid.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class MessageServiceImplementation implements MessageService {

    // Injecting dependencies for message repository, chat service, and chat repository
    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private ChatService chatService;

    @Autowired
    private ChatRepository chatRepository;

    @Override
    public Message createMessage(User user, Integer chatId, Message req) throws Exception {

        // Retrieving the chat object using the chat service
        Chat chat = chatService.findChatById(chatId);

        // Creating a new message instance
        Message message = new Message();

        // Setting up message details
        message.setChat(chat);
        message.setContent(req.getContent());
        message.setImage(req.getImage());
        message.setUser(user);
        message.setTimestamp(LocalDateTime.now()); // Setting current timestamp for the message

        // Saving the message in the repository
        Message savedMessage = messageRepository.save(message);

        // Adding the saved message to the chat's message list
        chat.getMessages().add(savedMessage);

        // Saving the updated chat object with the new message added
        chatRepository.save(chat);

        // Returning the saved message
        return savedMessage;
    }

    @Override
    public List<Message> findChatsMessages(Integer chatId) throws Exception {
        // Retrieving the chat by ID using the chat service
        Chat chat = chatService.findChatById(chatId);

        // Returning all messages associated with the chat
        return messageRepository.findByChatId(chatId);
    }
}
