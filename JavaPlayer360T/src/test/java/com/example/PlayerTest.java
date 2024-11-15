package com.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PlayerTest {

    private Player player;

    @BeforeEach
    void setUp() {
        player = new Player("Initiator");
    }

    @Test
    void testInitialState() {
        // Verify initial state of the player
        assertEquals("Initiator", player.getName());
        assertEquals("", player.getReceivedMessage());
        assertEquals(0, player.getMessageCount());
    }

    @Test
    void testSetName() {
        // Test setting player's name
        player.setName("Responder");
        assertEquals("Responder", player.getName());
    }

    @Test
    void testReceiveMessage() {
        // Test receiving a message
        String message = "Hello";
        String received = player.receiveMessage(message);
        assertEquals(message, received);
        assertEquals("Hello", player.getReceivedMessage());
    }

    @Test
    void testSendMessage() {
        // Test sending a message and incrementing message count
        player.sendMessage();
        assertEquals(1, player.getMessageCount());

        player.sendMessage();
        assertEquals(2, player.getMessageCount());
    }

    @Test
    void testReturnMessage() {
        // Test formatting of the return message after receiving a message and sending
        // messages
        player.receiveMessage("Hello there");
        player.sendMessage();

        String expectedMessage = "Initiator received: Hello there | Initiator's Message Count: 1";
        assertEquals(expectedMessage, player.returnMessage());
    }

    @Test
    void testConstructorWithMessage() {
        // Test the constructor that takes an initial message
        Player playerWithMessage = new Player("Responder", "Initial Message");
        assertEquals("Responder", playerWithMessage.getName());
        assertEquals("Initial Message", playerWithMessage.getReceivedMessage());
        assertEquals(0, playerWithMessage.getMessageCount());
    }
}