package com.mazid.request;

import lombok.Data;

// CreateChatRequest class is used to capture the user ID for creating a chat
@Data
public class CreateChatRequest {

    // ID of the user with whom the chat will be created
    private Integer userId;
}
