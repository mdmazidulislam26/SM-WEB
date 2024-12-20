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

    @PostMapping("api/chats")
    public Chat createChat(@RequestHeader("Authorization") String jwt,@RequestBody CreateChatRequest req) throws Exception {
        User reqUser = userService.findUserByJwt(jwt);
        User user2 = userService.findUserById(req.getUserId());
        Chat chat = chatService.createChat(reqUser,user2);

        return chat;
    }

    @GetMapping("api/chats")
    public List<Chat> findUserChat(@RequestHeader("Authorization") String jew){

        User user = userService.findUserByJwt(jew);
        List<Chat> chats = chatService.findUsersChat(user.getId());

        return chats;
    }
}
