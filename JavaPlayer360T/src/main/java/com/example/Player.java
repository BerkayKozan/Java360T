/**
 * The {@code Player} class represents a participant in a conversation.
 * Each player has a name, a record of the last received message, and a count of messages sent.
 *
 * <p>This class is used to simulate the behavior of a player (either an initiator or responder)
 * in a conversation. It provides methods to send messages, receive messages, and retrieve
 * relevant details about the player's state.
 * 
 * <p>Usage:
 * <ul>
 *   <li>Create a {@code Player} instance with a name and optional initial message.</li>
 *   <li>Use {@link #sendMessage()} to increment the message count when sending a message.</li>
 *   <li>Use {@link #receiveMessage(String)} to update the player's last received message.</li>
 *   <li>Retrieve the current state using {@link #getName()}, {@link #getReceivedMessage()}, or {@link #getMessageCount()}.</li>
 * </ul>
 * 
 * @author Berkay Kozan
 */

package com.example;

public class Player {
    private String name;
    private String receivedMessage;
    private int messageCount;

    public Player(String name) {
        this.name = name;
        this.receivedMessage = "";
        this.messageCount = 0;
    }

    public Player(String name, String message) {
        this.name = name;
        this.receivedMessage = message;
        this.messageCount = 0;
    }

    public String getName() {
        return name;
    }

    public String getReceivedMessage() {
        return receivedMessage;
    }

    public int getMessageCount() {
        return messageCount;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setReceivedMessage(String message) {
        this.receivedMessage = message;
    }

    public void sendMessage() {
        this.messageCount++;
    }

    public String receiveMessage(String message) {
        this.receivedMessage = message;
        return message;
    }

    public String returnMessage() {
        return this.name + " received: " + this.receivedMessage + " | " + this.name + "'s Message Count: "
                + this.messageCount;
    }
}
