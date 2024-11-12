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
        return this.name + " received: "+ this.receivedMessage + " | " + this.name + "'s Message Count: " + this.messageCount;
    }
}
