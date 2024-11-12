package com.example;
import java.util.Scanner;

public class SingleProcess {

    private static final int MAX_MESSAGES = 10;

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

    private static void handleMessageExchange(Scanner scanner, Player sender, Player receiver) {
        System.out.print(sender.getName() + ", enter your message #"+ (sender.getMessageCount() + 1) +": ");
        String message = scanner.nextLine();
        sender.sendMessage();
        receiver.receiveMessage(message);
        System.out.println(receiver.returnMessage());
    }
}
