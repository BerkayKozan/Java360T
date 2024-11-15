/**
 * The {@code SingleProcess} class simulates a conversation between two players 
 * (an initiator and a responder) in a single process. The players alternate sending 
 * and receiving messages until a maximum number of messages is reached.
 *
 * <p>This class uses the {@link Player} class to represent the participants in the conversation.
 * It facilitates message exchange between the initiator and responder through console input/output.
 * 
 * <p>Usage:
 * <ul>
 *   <li>Create {@link Player} objects to represent the initiator and responder.</li>
 *   <li>Use {@link #handleMessageExchange(Scanner, Player, Player)} to manage the interaction between players.</li>
 *   <li>The conversation alternates between the two players until {@value #MAX_MESSAGES} messages are exchanged.</li>
 * </ul>
 * 
 * @author Berkay Kozan
 */

package com.example;

import java.util.Scanner;

public class SingleProcess {

    public static final int MAX_MESSAGES = 10;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Player initiator = new Player("Initiator");
        Player responder = new Player("Responder");

        int totalMessageCount = 0;

        while (initiator.getMessageCount() < MAX_MESSAGES || responder.getMessageCount() < MAX_MESSAGES) {
            Player sender = (totalMessageCount % 2 == 0) ? initiator : responder;
            Player receiver = (totalMessageCount % 2 == 0) ? responder : initiator;
            handleMessageExchange(scanner, sender, receiver);
            totalMessageCount++;
        }
        System.out.println("Conversation finished after " + MAX_MESSAGES + " exchanges.");
    }

    public static void handleMessageExchange(Scanner scanner, Player sender, Player receiver) {
        String message;
        do {
            System.out.print(sender.getName() + ", enter your message #" + (sender.getMessageCount() + 1) + ": ");
            message = scanner.nextLine().trim(); // Trim whitespace to prevent spaces-only messages
            if (message.isEmpty()) {
                System.out.println("Message cannot be empty. Please enter a valid message.");
            }
        } while (message.isEmpty()); // Repeat until a non-empty message is entered

        if (message.isEmpty())
            return; // End if no more input

        sender.sendMessage();
        receiver.receiveMessage(message);
        System.out.println(receiver.returnMessage());
    }
}
