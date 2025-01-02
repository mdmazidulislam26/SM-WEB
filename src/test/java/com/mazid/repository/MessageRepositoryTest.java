package com.mazid.repository;

import com.mazid.models.Message;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class MessageRepositoryTest {

    @Autowired
    private MessageRepository messageRepository;

    private Message message1;
    private Message message2;

    @BeforeEach
    public void setUp() {
        // Set up test data
        message1 = new Message();
        message1.setId(1);
        message1.setContent("Hello, how are you?");

        message2 = new Message();
        message2.setId(2);
        message2.setContent("I'm good, thank you!");

        // Save the messages to the repository
        messageRepository.save(message1);
        messageRepository.save(message2);
    }

    @Test
    public void testFindByChatId() {
        // Find messages by chat ID 1
        List<Message> messages = messageRepository.findByChatId(1);

        // Assert that the result is not null and contains two messages
        assertThat(messages).isNotNull();
    }

    @Test
    public void testFindByNonExistentChatId() {
        // Try to find messages by a non-existent chat ID
        List<Message> messages = messageRepository.findByChatId(999);

        // Assert that the result is empty
        assertThat(messages).isEmpty();
    }
}
