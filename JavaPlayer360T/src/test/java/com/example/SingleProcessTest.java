package com.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SingleProcessTest {

    private Player initiator;
    private Player responder;

    @BeforeEach
    void setUp() {
        initiator = new Player("Initiator");
        responder = new Player("Responder");
    }

    @Test
    void testConversationEndsAfterMaxMessages() {
        // Prepare a series of messages for the initiator and responder
        String input = "Hi\nHello\nHow are you?\nI’m fine, thanks!\nWhat about you?\n" +
        "I'm doing well too!\nGood to hear!\nYes!\nNice!\nTake care!\n" +
        "What's up?\nNothing much!\nCool!\nSee you soon!\nGoodbye!\n" +
        "Take it easy!\nStay safe!\nEnjoy your day!\nThank you!\nYou're welcome!\nSee you!\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        Scanner scanner = new Scanner(in);

        int totalMessageCount = 0;
        while (initiator.getMessageCount() < SingleProcess.MAX_MESSAGES || responder.getMessageCount() < SingleProcess.MAX_MESSAGES) {
            Player sender = (totalMessageCount % 2 == 0) ? initiator : responder;
            Player receiver = (totalMessageCount % 2 == 0) ? responder : initiator;
            SingleProcess.handleMessageExchange(scanner, sender, receiver);
            totalMessageCount++;
        }

        // Assert that both players sent the expected number of messages
        assertEquals(SingleProcess.MAX_MESSAGES, initiator.getMessageCount());
        assertEquals(SingleProcess.MAX_MESSAGES, responder.getMessageCount());
    }

    @Test
    void testHandleMessageExchange() {
        // Mock Scanner to return a predefined message
        String input = "Test message\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        Scanner scanner = new Scanner(in);
        // Simulate a message exchange
        SingleProcess.handleMessageExchange(scanner, initiator, responder);

        // Verify the sender's message count and receiver's response
        assertEquals(1, initiator.getMessageCount());
        assertEquals("Responder received: Test message | Responder's Message Count: 0", responder.returnMessage());
    }

    @Test
    void testEmptyMessageExchange() {
        // Mock Scanner to return an empty message
        String input = "\nHello there!\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        Scanner scanner = new Scanner(in);
        // Simulate a message exchange with an empty message
        SingleProcess.handleMessageExchange(scanner, initiator, responder);

        // Verify the initiator's message count and receiver's response
        assertEquals(1, initiator.getMessageCount());
    }
}