package com.mazid.controller;

import com.mazid.models.Message;
import com.mazid.models.User;
import com.mazid.service.MessageService;
import com.mazid.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CreateMessage {

    @Autowired
    private MessageService messageService;

    @Autowired
    private UserService userService;

    // Endpoint to create a new message in a specific chat
    @PostMapping("api/messages/chat/{chatId}")
    public Message createMessage(@RequestBody Message req,
                                 @RequestHeader("Authorization") String jwt,
                                 @PathVariable Integer chatId) throws Exception {

        // Find the user from the JWT token
        User user = userService.findUserByJwt(jwt);

        // Create the new message in the specified chat
        Message message = messageService.createMessage(user, chatId, req);

        return message;
    }

    // Endpoint to retrieve all messages in a specific chat
    @GetMapping("api/messages/chat/{chatId}")
    public List<Message> findChatsMessage(@RequestHeader("Authorization") String jwt,
                                          @PathVariable Integer chatId) throws Exception {

        // Find the user from the JWT token
        User user = userService.findUserByJwt(jwt);

        // Retrieve all messages in the specified chat
        List<Message> messages = messageService.findChatsMessages(chatId);

        return messages;
    }
}
