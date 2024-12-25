package com.mazid.repository;

import com.mazid.models.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

// JpaRepository provides JPA related methods for working with Message entity.
public interface MessageRepository extends JpaRepository<Message, Integer> {

    // Method to find all messages associated with a specific chat by its chat ID.
    // This returns a list of messages for the specified chat.
    public List<Message> findByChatId(Integer chatId);
}
