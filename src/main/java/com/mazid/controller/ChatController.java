package com.mazid.controller;

import com.mazid.models.Chat;
import com.mazid.models.User;
import com.mazid.request.CreateChatRequest;
import com.mazid.service.ChatService;
import com.mazid.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ChatController {

    @Autowired
    private ChatService chatService;

    @Autowired
    private UserService userService;

    // Endpoint to create a new chat between two users
    @PostMapping("api/chats")
    public Chat createChat(@RequestHeader("Authorization") String jwt, @RequestBody CreateChatRequest req) throws Exception {

        // Find the user from the JWT token
        User reqUser = userService.findUserByJwt(jwt);

        // Find the second user by the user ID from the request
        User user2 = userService.findUserById(req.getUserId());

        // Create a new chat between the two users
        Chat chat = chatService.createChat(reqUser, user2);

        return chat;
    }

    // Endpoint to retrieve all chats for the authenticated user
    @GetMapping("api/chats")
    public List<Chat> findUserChat(@RequestHeader("Authorization") String jew){

        // Find the user from the JWT token
        User user = userService.findUserByJwt(jew);

        // Get the list of chats for the authenticated user
        List<Chat> chats = chatService.findUsersChat(user.getId());

        return chats;
    }
}
